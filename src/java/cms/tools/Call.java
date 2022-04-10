/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.access.Access_User;
import static cms.tools.sms.checkStatus;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import jj.jjDatabaseWeb;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mahdi
 */
public class Call {

    public static String apiKey = "2F576E315A7562634A38303670774648467057506E396B30414C4A4D357A4D65734663682B465A443776673D";
    public static String webService = "kavenegar.com";
    public static String tableName = "phonecall";
    public static String _id = "id";
    public static String _text = "phonecall_text";
    public static String _sender = "phonecall_sender";
    public static String _receiver = "phonecall_receiver";
    public static String _characters = "phonecall_characters";
    public static String _status = "phonecall_status";
    public static String _date = "phonecall_date";
    public static String _sendTime = "phonecall_send_time";
    public static String _messageID = "phonecall_messageID";
    public static String _webService = "phonecall_webService";
    public static String _receiverId = "phonecall_receiver_id";
    public static String _receiverName = "phonecall_receiver_name";
    public static String _receiverFamily = "phonecall_receiver_family";
    public static String _comment = "phonecall_comment";
    public static String _subject = "phonecall_subject";
    public static String emptyField = "فیلدهای خالی را پر کنید.";
    public static String lbl_send = "ارسال پیامک";
//    public static int rul_rfs = 51;
//    public static int rul_ins = 52;
//    public static int rul_edt = 53;
//    public static int rul_dlt = 54;
//    public static int reserved=55;

    //   public static int rul_show_pics = 401;//نمایش تب پیامک//====== BY RASHIDI ======
//    public static int rul_show_SMS _reserved= 402 --- 420;// RESERVED : 402 -- 420//نمایش تب پیامک//====== BY RASHIDI ======
    public static int rul_rfs = 0;
    public static int rul_ins = 0;
    public static int rul_edt;
    public static int rul_dlt = 0;
//    public static int rul_rfs = 421;
//    public static int rul_ins = 422;
//    public static int rul_edt;
//    public static int rul_dlt = 423;
//    public static int rul_reserved = 424 --- 440;// RESERVED : 424 -- 440
////// ---------------- insert() ------------------->

    public static String sendCallByApi(HttpServletRequest request, jjDatabaseWeb db, String receptor, String message, String date, String type, String localid) throws IOException, SQLException, Exception {
        try {
            URL url = new URL("https://api.kavenegar.com/v1/2F576E315A7562634A38303670774648467057506E396B30414C4A4D357A4D65734663682B465A443776673D/call/maketts.json?receptor=" + receptor + "&&message=" + URLEncoder.encode(message,"UTF-8")+""); 
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//خط بالا با استفاده از اچ تی تی پی یو ار ال کانکشن می توان صفخه وب را باز کرد و اطلاعات ان را داخل کانکشن بریزیم  
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //کد بالا اطلاعات گرفته از صفحه وب را که ممکن است هرچیزی باشد را به استرینگ تبدیل می کند ودر این ذخیره می شود
            String inputLine;
            String result = "[";
            //در جی سان رشته با براکت باز آغاز می شود وبا براکت بسته پایان می یابد ولی تلگرام ازاین قاعده پیروی نمی کند برای همین باید براکت باز وبسته را در اول اخر ان تعریف کرد.
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
                //اگر این اطلاعاتی که به استرینگ تبدیل کردیم داخلش نال نبود به ریزالت اضافه کن 
            }
            result += "]";
            in.close();
            System.out.println("----------------------------------------");
            System.out.println(result);
            System.out.println("--------------------------------");
            JSONArray array;
// اطلاعاتی که داخل یو آر ال می باشد با فرمت جی سان می باشد این اطلاعات را داخل یک 
//متغیری از جی سان به نام array  تعری0ف میکنیم
            array = new JSONArray(result);
            JSONObject jsonObj = array.getJSONObject(0);

            System.out.println("++++++++++++++++++++++++++++++++++++++++");
            System.out.println(jsonObj.toString());//
            System.out.println(jsonObj.get("return"));
            JSONArray entries = jsonObj.getJSONArray("entries");
            System.out.println("messageid=" + entries.getJSONObject(0).get("messageid"));
            System.out.println("date=" + entries.getJSONObject(0).get("date"));
            System.out.println("message=" + entries.getJSONObject(0).get("message"));
            System.out.println("cost=" + entries.getJSONObject(0).get("cost"));
            System.out.println("status=" + entries.getJSONObject(0).get("status"));
            System.out.println("statustext=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("message=" + entries.getJSONObject(0).get("statustext"));
            System.out.println("sender=" + entries.getJSONObject(0).get("sender"));
            //برای گرفتن اطلاعات کلید دوم از گت استفاده میکنیم 
            System.out.println("++++++++++++++++++++++++++++++++");
            int status = Integer.valueOf(jsonObj.getJSONObject("return").get("status").toString());
            List<Map<String, Object>> userIdRow = jjDatabaseWeb.separateRow(db.Select(Access_User.tableName, Access_User._mobile + "  LIKE '%" + receptor + "%'"));
            Map<String, Object> smsMap = new HashMap<>();
            if (userIdRow.size() == 1) {//اگر آیدی یکی بود در جدول ثبت شود هم نام و هم فامیلی
                int userId = Integer.valueOf(userIdRow.get(0).get(Access_User._id).toString());
                smsMap.put(_receiverId, userId);
                smsMap.put(_receiverName, userIdRow.get(0).get(Access_User._name));
                smsMap.put(_receiverFamily, userIdRow.get(0).get(Access_User._family));
            }

            String resultStatus = checkStatus(status);
            smsMap.put(_messageID, entries.getJSONObject(0).get("messageid"));
            smsMap.put(_receiver, receptor);
            smsMap.put(_date, entries.getJSONObject(0).get("date"));
            smsMap.put(_text, message);
            smsMap.put(_sender, entries.getJSONObject(0).get("sender"));
            smsMap.put(_status, resultStatus);
            db.insert(Call.tableName, smsMap);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("آدرس وب نامعتبر"); //اگر یو ار ال درست نباشد یا به اینترنت وصل نباشیم این اررور را به ما می دهد
            ex.printStackTrace();
        } catch (JSONException ex) {
            System.out.println("json error");
            ex.printStackTrace();
        }
        return "";
    }

}
