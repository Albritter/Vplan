

import org.json.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Created by albritter on 13.08.16.
 */
public final class Request {
    private static String sessionId;

    private static int currentRequest = 0;

    public static synchronized boolean authenticate(String user, String password) throws IOException {
        synchronized (Request.class) {
            String str = "{\"id\":\"0001101\",\"method\":\"authenticate\",\"params\":{\"user\":\"" + URLEncoder.encode(user, "UTF-8") + "\", \"password\":\"\", \"client\":\"Vplan\"},\"jsonrpc\":\"2.0\"}";
            JSONObject bo = genericRequest(str);
            System.out.println(bo);
            sessionId = bo.getJSONObject("result").getString("sessionId");
            System.out.println("SessionID=" + sessionId);
            return false;
        }
    }

    private synchronized static JSONObject genericRequest(String post) {
        synchronized (Request.class) {

            HttpURLConnection conn = null;
            JSONObject bo = null;
            try {
                conn = (HttpURLConnection) new URL("https://hepta.webuntis.com/WebUntis/jsonrpc.do?school=bbs1%20stade").openConnection();
                conn.addRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                if (sessionId != null)
                    conn.addRequestProperty("Cookie", "JSESSIONID=" + sessionId);
                OutputStreamWriter body = new OutputStreamWriter(conn.getOutputStream());
                body.write(post);
                body.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                bo = new JSONObject(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bo;
        }
    }

    public static synchronized JSONObject executeRequset(String i, ArrayList<Object[]> params) {
        synchronized (Request.class) {
            StringBuilder build = new StringBuilder();

            if (params != null) {
                for (Object[] a : params) {
                    if (a[1] instanceof String) {
                        build.append("\"" + a[0] + "\":\"" + a[1] + "\",");

                    } else
                        build.append("\"" + a[0] + "\":" + a[1] + ",");
                }
                build.deleteCharAt(build.lastIndexOf(","));
            } else {
                build = new StringBuilder();
            }
            currentRequest = 0x80000000 ^ (0x80000000 | (new Random()).nextInt());
            String body = "{\"id\":\"" + currentRequest + "\",\"method\":\"" + i + "\",\"params\":{" + build.toString() + "},\"jsonrpc\":\"2.0\"}";
            System.out.println(body);
            JSONObject json = genericRequest(body);
            System.out.println(json);
            return null;
        }
    }

    public static void main(String[] argv) throws IOException {
        try {
            disableSSLVerification();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        ArrayList<Object[]> a = new ArrayList<Object[]>();
        a.add(new Object[]{
                "id", 267
        });
        a.add(new Object[]{
                "type", 1
        });
        authenticate("it15", null);
        executeRequset(Type.ELEMENT_TIMETABLE, a);
        executeRequset(Type.LOGOUT, null);
    }

    private static void disableSSLVerification() throws KeyManagementException, NoSuchAlgorithmException {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

    }

    @SuppressWarnings("unused")
    public static final class Type {
        public static final String LOGOUT = "logout";
        public static final String TEACHER = "getTeachers";
        public static final String STUDENTS = "getStudents";
        public static final String BASE_CLASSES = "getKlassen";
        public static final String SUBJECTS = "getSubjects";
        public static final String ROOMS = "getRooms";
        public static final String DEPARTMENTS = "getDepartments";
        public static final String HOLIDAYS = "getHolidays";
        public static final String TIME_GRID = "getTimegridUnits";
        public static final String STATUS_DATA = "getStatusData";
        public static final String CURRENT_SCHOOL_YEAR = "getCurrentSchoolyears";
        public static final String SCHOOL_YEARS = "getSchoolyears";
        public static final String ELEMENT_TIMETABLE = "getTimetable";
        public static final String LAST_IMPORT = "getLatestImportTime";
        public static final String PERSON_ID = "getPersonId";
        public static final String SUBSTITUTIONS = "getSubstitutions";
        public static final String CLASS_REG_EVENTS = "getClassregEvents";
        public static final String EXAMS = "getExams";
        public static final String EXAM_TYPE = "getExamTypes";
    }

    public static int getCurrentRequest() {
        return currentRequest;
    }
}