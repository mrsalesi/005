/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.cms;

import cms.access.Access_User;
import cms.tools.Js;
import cms.tools.Server;
import cms.tools.jjTools;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jj.jjDatabase;
import jj.jjDatabaseWeb;
import jj.jjNumber;

/**
 *
 * @author Mohammad
 */
public class Tice_config {

    public static String tableName = "tice_config";
    public static String _id = "id";
    public static String _config_Name = "Tice_config_Name";
    public static String _config_value = "Tice_config_value";
    public static String _config_smsNumber_name = "config_smsNumber_name";
    public static String _config_regexPass_name = "config_regexPass_name";
    public static String _config_activeSms_name = "config_activeSms_name";
    public static String _config_sendSessionToCommunicatorRole_name = "config_sendSessionToCommunicatorRole_name";
    public static String _config_invitationCommittee_name = "config_invitationCommittee_name";
    public static String _config_sendPlantoSupervisor_name = "config_sendPlantoSupervisor_name";
    public static String _config_communicateofSessionApprovals_name = "config_communicateofSessionApprovals_name";
    public static String _config_sendPlantoImproveQuality_name = "config_sendPlantoImproveQuality_name";
    public static String _config_sendPlantoManager_name = "config_sendPlantoManager_name";
    public static String _config_communicateofPlanSteps_name = "config_communicateofPlanSteps_name";
    public static String _config_sendPlantoCommittee_name = "config_sendPlantoCommittee_name";
    public static String _config_requestEditingthePlan_name = "config_requestEditingthePlan_name";
    public static String _config_offerFormtotheCommittee_name = "config_offerFormtotheCommittee_name";
    public static String _config_rejectOffer_name = "config_rejectOffer_name";
    public static String _config_acceptOffer_name = "config_acceptOffer_name";
    public static String _config_apiKey_sms_name = "config_apiKey_sms_name";
    public static String _config_emailAccount_name = "config_emailAccount_name";
    public static String _config_passEmail_name = "config_passEmail_name";
    public static String _config_activeEmail_name = "config_activeEmail_name";
    public static String _config_reminderDayBeforeStepsStartDate_name = "config_reminderDayBeforeStepsStartDate_name";
    public static String _config_reminderDayBeforeStepsEndDate_name = "config_reminderDayBeforeStepsEndDate_name";
    public static String _config_reminderDayBeforeApprovedStartDate_name = "config_reminderDayBeforeApprovedStartDate_name";
    public static String _config_reminderDayBeforeApprovedEndDate_name = "config_reminderDayBeforeApprovedEndDate_name";
    public static String _config_reminderDayBeforeNextSessions_name = "config_reminderDayBeforeNextSessions_name";
    public static String _config_reminderDayBeforeNextCreateDocumentary_name = "config_reminderDayBeforeNextCreateDocumentary_name";
    public static String _config_activeSmsModuleCommittee_name = "config_activeSmsModuleCommittee_name";
    public static String _config_activeEmailModuleCommittee_name = "config_activeEmailModuleCommittee_name";
    public static String _config_activeSmsModulePlans_name = "config_activeSmsModulePlans_name";
    public static String _config_activeEmailModulePlans_name = "config_activeEmailModulePlans_name";
    public static String _config_activeSmsModuleIndicators_name = "config_activeSmsModuleIndicators_name";
    public static String _config_activeEmailModuleIndicators_name = "config_activeEmailModuleIndicators_name";
    public static String _config_activeSmsModulePosition_name = "config_activeSmsModulePosition_name";
    public static String _config_activeEmailModulePosition_name = "config_activeEmailModulePosition_name";
    public static String _config_activeSmsLogin_name = "config_activeSmsLogin_name";
    public static String _config_activeEmailLogin_name = "config_activeEmailLogin_name";
    public static String _config_activeSmsChangeProfile_name = "config_activeSmsChangeProfile_name";
    public static String _config_activeEmailChangeProfile_name = "config_activeEmailChangeProfile_name";
    public static String _config_activeSmsModuleDocumentary_name = "config_activeSmsModuleDocumentary_name";
    public static String _config_activeEmailModuleDocumentary_name = "config_activeEmailModuleDocumentary_name";
    public static String _config_activeSmsModuleStrategic_name = "config_activeSmsModuleStrategic_name";
    public static String _config_activeEmailModuleStrategic_name = "config_activeEmailModuleStrategic_name";
    public static String _config_loginMobileWithToken_name = "config_loginMobileWithToken_name";
    public static String _config_loginMobileWithTokenJustUniqDevice_name = "config_loginMobileWithTokenJustUniqDevice_name";
    public static String _config_exchange_unite = "config_exchange_unite";
    public static String _config_companyName = "config_companyName";
    public static String _config_nationalCode = "config_nationalCode";
    public static String _config_economicCode = "config_economicCode";
    public static String _config_addressCompany = "config_addressCompany";
    public static String _config_tellCompany = "config_tellCompany";
    public static String _config_zipcodeCompany = "config_zipcodeCompany";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ثبت ویرایش";
    public static int rul_rfs = 610;
    public static int rul_edt = 611;

    /**
     * این متد نام پارامتر مورد نظر را می گیرد و در جدول تنظیمات مقدار آن را بر
     * می گرداند
     * <br/>
     * SELECT FROM tice_config WHERE Tice_config_Name='_config_Name'
     *
     * @param db
     * @param Tice_config_Name
     * @return Tice_config_value
     */
    public static String getValue(jjDatabaseWeb db, String Tice_config_Name) {
        try {
            List<Map<String, Object>> oneConfigRow = jjDatabase.separateRow(db.Select(tableName, _config_value, _config_Name + "='" + Tice_config_Name + "'"));
            if (oneConfigRow.isEmpty()) {//اگر چنین چیزی وجود نداشت تهی برگرداند تا آنطرف ایجادش کند                
                return "";
            } else {
                return oneConfigRow.get(0).get(_config_value).toString();
            }
        } catch (Exception ex) {
//            Server.ErrorHandler("پارامتر مورد نظر د جدول وجود ندارد");
            return Server.ErrorHandler(ex);
        }
    }

    public static String setNewConfiguration(jjDatabaseWeb db, String Tice_config_Name, String Tice_config_Value) {
        try {
            List<Map<String, Object>> oneConfigRow = jjDatabase.separateRow(db.Select(tableName, _config_value, _config_Name + "='" + Tice_config_Name + "'"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_config_Name, Tice_config_Name);
            map.put(_config_value, Tice_config_Value);
            if (oneConfigRow.isEmpty()) {//اگر چنین چیزی وجود نداشت ایجادکن تا تکرار پیش نیاید
                db.insert(tableName, map);
                return "";
            } else {// اگر وجود داشت بروز رسانی کن
                db.update(tableName, map, _config_Name + "='" + Tice_config_Name + "'");
            }
            return "";
        } catch (Exception ex) {
//            Server.ErrorHandler("پارامتر مورد نظر د جدول وجود ندارد");
            return Server.ErrorHandler(ex);
        }
    }

    public static String refresh(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean isFromClient) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_rfs)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }

            StringBuilder html = new StringBuilder();
            html.append("<div class=\"card-header bg-primary tx-white\">لیست تنظیمات</div>\n");
            html.append(" "
                    + "<div class='col-lg-12'>"
                    + "<a href='#' class='sh-pagetitle-icon'>"
                    + "<div style='font-size:33px'><i class='fa fa-refresh mg-t-30' onclick='hmisTiceConfig.m_refresh();'></i>"
                    + "</div>"
                    + "</a>"
                    + "</div>"
                    + "");

            html.append("<div class=\"table-wrapper\">\n");
            html.append("<div class=\"table-wrapper\">\n");
            html.append("<table id='refreshTiceConfig' class='table display' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th width='40%'>نام</th>");
            html.append("<th width='40%'>مقدار</th>");
            html.append("<th width='40%'>ویرایش</th>");
            html.append("</thead><tbody>");

            List<Map<String, Object>> row = jjDatabase.separateRow(db.Select(Tice_config.tableName));

            for (int i = 0; i < row.size(); i++) {
                html.append("<tr  class='mousePointer' onclick='hmisTiceConfig.m_select(" + row.get(i).get(Tice_config._id).toString() + ");'>");
                html.append("<td class='c'>" + row.get(i).get(Tice_config._config_Name) + "</td>");
                html.append("<td class='r'>" + row.get(i).get(Tice_config._config_value) + "</td>");
                html.append("<td class='r'><i class='icon ion-gear-a' style='color:#a02311'></i></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            html.append("<script>"
                    + "</script>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "auto";
            }
            if (panel.equals("")) {
                panel = "swTiceConfigTbl";
            }
            String html2 = Js.setHtml("#" + panel, html.toString());
            html2 += Js.table("#refreshTiceConfig", "auto", 0, "", "جلسات");
            Server.outPrinter(request, response, html2);
            return "";
        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String select(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> ConfigRow = jjDatabase.separateRow(db.Select(tableName, _config_value + "," + _config_Name, _id + "=" + id));
            Map<String, Object> map = new HashMap<String, Object>();
            String script = "";
            StringBuilder html = new StringBuilder();
            StringBuilder html2 = new StringBuilder();
            html.append("<div class=\"col-lg-12\">");
            if (ConfigRow.get(0).get(_config_Name).equals(_config_companyName)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    نام شرکت:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_companyName' name='config_companyName' class=\"form-control is-valid\" value='" + getValue(db, _config_companyName) + "' placeholder=\"نام شرکت را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_nationalCode)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    شناسه ملی / کد ملی:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_nationalCode' name='config_nationalCode' class=\"form-control is-valid\" value='" + getValue(db, _config_nationalCode) + "' placeholder=\"شماره ملی را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_economicCode)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    شماره اقتصادی شرکت:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_economicCode' name='config_economicCode' class=\"form-control is-valid\" value='" + getValue(db, _config_economicCode) + "' placeholder=\"شماره اقتصادی را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_addressCompany)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    ادرس شرکت:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_addressCompany' name='config_addressCompany' class=\"form-control is-valid\" value='" + getValue(db, _config_addressCompany) + "' placeholder=\"آدرس شرکت را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_tellCompany)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    شماره تلفن شرکت را وارد کنید:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_tellCompany' name='config_tellCompany' class=\"form-control is-valid\" value='" + getValue(db, _config_tellCompany) + "' placeholder=\"شماره تلفن شرکت را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_zipcodeCompany)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    کد پستی شرکت:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_zipcodeCompany' name='config_zipcodeCompany' class=\"form-control is-valid\" value='" + getValue(db, _config_zipcodeCompany) + "' placeholder=\"کد پستی شرکت را وارد کنید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_exchange_unite)) {
                html.append("               <div class='col-lg-12'>\n"
                        + "                 واحد پول خود را انتخاب کنید:"
                        + "                    <div class='form-group has-success mg-b-0'>\n"
                        + "<select class='form-control' id='config_exchange_unite' name='config_exchange_unite' value='" + getValue(db, _config_exchange_unite) + "'>\n");
                if (getValue(db, _config_exchange_unite).equals("تومان")) {
                    html.append("    <option value='تومان' selected='selected'>تومان</option>"
                            + " <option value='ریال'>ریال</option>"
                            + "<option value='دلار'>دلار</option>");
                }
                if (getValue(db, _config_exchange_unite).equals("ریال")) {
                    html.append("    <option value='تومان'>تومان</option>"
                            + " <option value='ریال' selected='selected'>ریال</option>"
                            + "<option value='دلار'>دلار</option>");
                }
                if (getValue(db, _config_exchange_unite).equals("دلار")) {
                    html.append("    <option value='تومان'>تومان</option>"
                            + " <option value='ریال'>ریال</option>"
                            + "<option value='دلار' selected='selected'>دلار</option>");
                }
                if (getValue(db, _config_exchange_unite).equals("")) {
                    html.append("    <option value='تومان'>تومان</option>"
                            + " <option value='ریال'>ریال</option>"
                            + "<option value='دلار'>دلار</option>");
                }
                html.append("                                       </select> "
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_smsNumber_name)) {
                html.append("<div class=\"col-lg-12\">\n"
                        + "                    شماره پیامکی سامانه کاوه نگار:\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_smsNumber_value' name='config_smsNumber_value' class=\"form-control is-valid\" value='" + getValue(db, _config_smsNumber_name) + "' placeholder=\"شماره مورد نظر را وارد نمایید\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col    -->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_regexPass_name)) {
                html.append("  <div class=\"col-lg-12\">\n"
                        + "                    عبارات منظم برای پسورد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_regexPass_value' value='" + getValue(db, _config_regexPass_name) + "' name='config_regexPass_value' class=\"form-control is-valid\" placeholder=\"regular experssion\"  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }

            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSms_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSms_value' name='config_activeSms_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSms_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsLogin_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک  هنگام ورود به سامانه فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsLogin_value' name='config_activeSmsLogin_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsLogin_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailLogin_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل هنگام ورود به سامانه فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailLogin_value' name='config_activeEmailLogin_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailLogin_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModulePlans_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول برنامه عملیاتی فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailModulePlans_value' name='config_activeEmailModulePlans_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModulePlans_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModulePlans_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک ماژول برنامه عملیاتی فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModulePlans_value' name='config_activeSmsModulePlans_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModulePlans_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModuleIndicators_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول شاخص فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailModuleIndicators_value' name='config_activeEmailModuleIndicators_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModuleIndicators_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModuleIndicators_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک ماژول شاخص فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleIndicators_value' name='config_activeSmsModuleIndicators_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModuleIndicators_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModulePosition_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول جایگاه فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailModulePosition_value' name='config_activeEmailModulePosition_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModulePosition_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModulePosition_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک ماژول جایگاه فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModulePosition_value' name='config_activeSmsModulePosition_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModulePosition_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModuleCommittee_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    پیامک ماژول کمیته ها فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleCommittee_value' name='config_activeSmsModuleCommittee_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModuleCommittee_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModuleCommittee_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول کمیته ها فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailModuleCommittee_value' name='config_activeEmailModuleCommittee_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModuleCommittee_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmail_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmail_value' name='config_activeEmail_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmail_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailChangeProfile_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeEmailChangeProfile_value' name='config_activeEmailChangeProfile_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailChangeProfile_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }

            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsChangeProfile_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول مستندات (امضا) فعال باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsChangeProfile_value' name='config_activeSmsChangeProfile_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsChangeProfile_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModuleDocumentary_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول مستندات (امضا) پیامک باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleDocumentary_value' name='config_activeSmsModuleDocumentary_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModuleDocumentary_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModuleDocumentary_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول مستندات (امضا) پیامک باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleDocumentary_value' name='config_activeSmsModuleDocumentary_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModuleDocumentary_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSmsModuleStrategic_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول استراتژیک (امضا) پیامک باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleStrategic_value' name='config_activeSmsModuleStrategic_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeSmsModuleStrategic_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmailModuleStrategic_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ایمیل ماژول استراتژیک (امضا) پیامک باشد\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_activeSmsModuleStrategic_value' name='config_activeSmsModuleStrategic_value' class=\"form-control is-valid\" value='" + getValue(db, _config_activeEmailModuleStrategic_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }

            if (ConfigRow.get(0).get(_config_Name).equals(_config_sendSessionToCommunicatorRole_name)) {
                html.append("            <div class=\"col-lg-12\">\n"
                        + "                  پیام  ارسال صورتجلسه به مسئول ابلاغ\n"
                );
                html.append("                  <div class=\"form-group has-success mg-b-0\">\n");
                html.append("<input id='config_sendSessionToCommunicatorRole_value' name='config_sendSessionToCommunicatorRole_value' value='" + getValue(db, _config_sendSessionToCommunicatorRole_name) + "' class=\"form-control is-valid\" type=\"text\" >\n");
                html.append("                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_apiKey_sms_name)) {
                html.append("            <div class=\"col-lg-12\">\n"
                        + "                  Api پیام"
                );
                html.append("                  <div class=\"form-group has-success mg-b-0\">\n");
                html.append("<input id='config_apiKey_sms_value' name='config_apiKey_sms_value' value='" + getValue(db, _config_apiKey_sms_name) + "' class=\"form-control is-valid\" type=\"text\" >\n");
                html.append("                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_emailAccount_name)) {
                html.append("            <div class=\"col-lg-12\">\n"
                        + "                  حساب ایمیل"
                );
                html.append("                  <div class=\"form-group has-success mg-b-0\">\n");
                html.append("<input id='config_emailAccount_value' name='config_emailAccount_value' value='" + getValue(db, _config_emailAccount_name) + "' class=\"form-control is-valid\" type=\"text\" >\n");
                html.append("                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_passEmail_name)) {
                html.append("            <div class=\"col-lg-12\">\n"
                        + "                پسورد ایمیل"
                );
                html.append("                  <div class=\"form-group has-success mg-b-0\">\n");
                html.append("<input id='config_passEmail_value' name='config_passEmail_value' value='" + getValue(db, _config_passEmail_name) + "' class=\"form-control is-valid\" type=\"text\" >\n");
                html.append("                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }

            if (ConfigRow.get(0).get(_config_Name).equals(_config_invitationCommittee_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                   پیام ارسال دعوتنامه\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_invitationCommittee_value' name='config_invitationCommittee_value' class=\"form-control is-valid\"   value='" + getValue(db, _config_invitationCommittee_name) + "' type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_sendPlantoSupervisor_name)) {
                html.append("            <div class=\"col-lg-12\">\n"
                        + "                   پیام ارسال برنامه عملیاتی به مافوق\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_sendPlantoSupervisor_value' name='config_sendPlantoSupervisor_value' class=\"form-control is-valid\"  value='" + getValue(db, _config_sendPlantoSupervisor_name) + "'  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_communicateofSessionApprovals_name)) {

                html.append("  <div class=\"col-lg-12\">\n"
                        + "                   پیام ابلاغ مصوبات صورتجلسه\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_communicateofSessionApprovals_value ' name='config_communicateofSessionApprovals_value' class=\"form-control is-valid\" value='" + getValue(db, _config_communicateofSessionApprovals_name) + "'   type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_offerFormtotheCommittee_name)) {

                html.append("               <div class=\"col-lg-12\">\n"
                        + "                    پیام ارسال فرم پیشنهادی به کمیته\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_offerFormtotheCommittee_value' name='config_offerFormtotheCommittee_value' class=\"form-control is-valid\"  value='" + getValue(db, _config_offerFormtotheCommittee_name) + "'  type=\"text\" >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_requestEditingthePlan_name)) {

                html.append("           <div class=\"col-lg-12\">\n"
                        + "                  پیام  درخواست ویرایش برنامه عملیاتی\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_requestEditingthePlan_value' name='config_requestEditingthePlan_value' class=\"form-control is-valid\"    type='text' value='" + getValue(db, _config_requestEditingthePlan_name) + "' >\n"
                        + "                    </div> \n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_sendPlantoCommittee_name)) {

                html.append("          <div class=\"col-lg-12\">\n"
                        + "                  پیام  ارسال برنامه عملیاتی به کمیته\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_sendPlantoCommittee_value' name='config_sendPlantoCommittee_value' class=\"form-control is-valid\"    type=\"text\"  value='" + getValue(db, _config_sendPlantoCommittee_name) + "'>\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_communicateofPlanSteps_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 پیام   ابلاغ گام های برنامه عملیاتی\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_communicateofPlanSteps_value' name='config_communicateofPlanSteps_value' class=\"form-control is-valid\"    type=\"text\" value='" + getValue(db, _config_communicateofPlanSteps_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_sendPlantoManager_name)) {
                html.append("           <div class=\"col-lg-12\">\n"
                        + "                  پیام  ارسال برنامه عملیاتی به مدیر\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_sendPlantoManager_value' name='config_sendPlantoManager_value' class=\"form-control is-valid\"   type=\"text\" value='" + getValue(db, _config_sendPlantoManager_name) + "'>\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_sendPlantoImproveQuality_name)) {

                html.append("               <div class=\"col-lg-12\">\n"
                        + "                   پیام ارسال برنامه عملیاتی به مسئول بهبود کیفیت\n"
                        + "                    (مسئول پایش)\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_sendPlantoImproveQuality_value' name='config_sendPlantoImproveQuality_value' class=\"form-control is-valid\"    type='text' value='" + getValue(db, _config_sendPlantoImproveQuality_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_rejectOffer_name)) {

                html.append("               <div class=\"col-lg-12\">\n"
                        + "                  پیام رد کردن مصوبه پیشنهادی"
                        + "                    (مسئول پایش)\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_rejectOffer_value' name='config_rejectOffer_value' class=\"form-control is-valid\"    type='text' value='" + getValue(db, _config_sendPlantoImproveQuality_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_acceptOffer_name)) {

                html.append("               <div class=\"col-lg-12\">\n"
                        + "                   پیام پذیرش مصوبه پیشنهادی "
                        + "                    (مسئول پایش)\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_acceptOffer_value' name='config_acceptOffer_value' class=\"form-control is-valid\"    type='text' value='" + getValue(db, _config_sendPlantoImproveQuality_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }

            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeStepsStartDate_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل از شروع انجام فرصت گام:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeStepsStartDate_value' name='config_reminderDayBeforeStepsStartDate_value' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeStepsStartDate_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeStepsEndDate_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل  از پایان انجام فرصت گام:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeStepsEndDate_value' name='config_reminderDayBeforeStepsEndDate_value' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeStepsEndDate_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeApprovedStartDate_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل ازشروع انجام فرصت مصوبه:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeApprovedStartDate_value' name='config_reminderDayBeforeApprovedStartDate_value' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeApprovedStartDate_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeApprovedEndDate_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل از پایان انجام فرصت مصوبه:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeApprovedEndDate_value' name='config_reminderDayBeforeApprovedEndDate_value' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeApprovedEndDate_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeNextSessions_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل از تاریخ جلسه بعد هر صورتجلسه:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeNextSessions_value' name='config_reminderDayBeforeNextSessions_value' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeNextSessions_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_reminderDayBeforeNextCreateDocumentary_name)) {
                html.append("               <div class=\"col-lg-12\">\n"
                        + "                 تعداد روز برای یادآوری قبل از تاریخ بازنگری مستند هر مستند:"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_reminderDayBeforeNextCreateDocumentary_name' name='config_reminderDayBeforeNextCreateDocumentary_name' class=\"form-control is-valid\"    type='number' value='" + getValue(db, _config_reminderDayBeforeNextCreateDocumentary_name) + "' >\n"
                        + "                    </div> <!--form-group -->\n"
                        + "                </div> <!--col-->\n"
                        + "");
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_loginMobileWithToken_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ورود با توکن در اپ\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_loginMobileWithToken_value' name='config_loginMobileWithToken_value' class=\"form-control is-valid\" value='" + getValue(db, _config_loginMobileWithToken_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_loginMobileWithTokenJustUniqDevice_name)) {
                html.append("             <div class=\"col-lg-12\">\n"
                        + "                    ورد با توکن فقط با یک گوشی همراه\n"
                        + "                    <div class=\"form-group has-success mg-b-0\">\n"
                        + "                        <input id='config_loginMobileWithTokenJustUniqDevice_value' name='config_loginMobileWithTokenJustUniqDevice_value' class=\"form-control is-valid\" value='" + getValue(db, _config_loginMobileWithTokenJustUniqDevice_name) + "'   type=\"checkbox\"  >\n"
                        + "                    </div> "
                        + "                </div>");

            }
            html.append(" </div>");
//            if (accEdt) {
            html2.append("<div class=\"col-lg-12\">");
            html2.append("<button class='btn btn-outline-warning btn-block mg-b-10 tahoma10' id=\"edit_ticeConfig\" onclick='hmisTiceConfig.m_edit(" + id + ");'> " + lbl_edit + "</button>");
            html2.append("</div>");
//            }

            script += Js.setHtml("#configForm", html);
            script += Js.setHtml("#config_button", html2);
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeSms_name)) {
                if (getValue(db, _config_activeSms_name).equals("1")) {
                    script += Js.setAttr("#config_activeSms_value", "checked", "checked");
                } else {
                    script += Js.removeAttr("#config_activeSms_value", "checked");
                }
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_activeEmail_name)) {
                if (getValue(db, _config_activeEmail_name).equals("1")) {
                    script += Js.setAttr("#config_activeEmail_value", "checked", "checked");
                } else {
                    script += Js.removeAttr("#config_activeEmail_value", "checked");
                }
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_loginMobileWithTokenJustUniqDevice_name)) {
                if (getValue(db, _config_loginMobileWithTokenJustUniqDevice_name).equals("1")) {
                    script += Js.setAttr("#config_loginMobileWithTokenJustUniqDevice_value", "checked", "checked");
                } else {
                    script += Js.removeAttr("#config_loginMobileWithTokenJustUniqDevice_value", "checked");
                }
            }
            if (ConfigRow.get(0).get(_config_Name).equals(_config_loginMobileWithToken_name)) {
                if (getValue(db, _config_loginMobileWithToken_name).equals("1")) {
                    script += Js.setAttr("#config_loginMobileWithToken_value", "checked", "checked");
                } else {
                    script += Js.removeAttr("#config_loginMobileWithToken_value", "checked");
                }
            }
            
            Server.outPrinter(request, response, script);
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }

    public static String edit(HttpServletRequest request, HttpServletResponse response, jjDatabaseWeb db, boolean needString) throws Exception {
        try {
            if (!Access_User.hasAccess(request, db, rul_edt)) {
                Server.outPrinter(request, response, "شما اجازه ی دسترسی به این قسمت را ندارید");
                return "";
            }
            String id = jjTools.getParameter(request, _id);
            List<Map<String, Object>> ConfigRow = jjDatabase.separateRow(db.Select(tableName, _config_value + "," + _config_Name, _id + "=" + id));
            if (!jjTools.getParameter(request, "config_smsNumber_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_smsNumber_value"));
            }
            if (!jjTools.getParameter(request, "config_regexPass_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_regexPass_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSms_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSms_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmail_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmail_value"));
            }
            if (!jjTools.getParameter(request, "config_apiKey_sms_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_apiKey_sms_value"));
            }
            if (!jjTools.getParameter(request, "config_emailAccount_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_emailAccount_value"));
            }
            if (!jjTools.getParameter(request, "config_passEmail_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_passEmail_value"));
            }
            if (!jjTools.getParameter(request, "config_sendSessionToCommunicatorRole_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_sendSessionToCommunicatorRole_value"));

            }
            if (!jjTools.getParameter(request, "config_invitationCommittee_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_invitationCommittee_value"));
            }
            if (!jjTools.getParameter(request, "config_rejectOffer_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_rejectOffer_value"));
            }
            if (!jjTools.getParameter(request, "config_acceptOffer_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_acceptOffer_value"));
            }
            if (!jjTools.getParameter(request, "config_communicateofSessionApprovals_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_communicateofSessionApprovals_value"));
            }
            if (!jjTools.getParameter(request, "config_sendPlantoSupervisor_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_sendPlantoSupervisor_value"));
            }
            if (!jjTools.getParameter(request, "config_sendPlantoImproveQuality_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_sendPlantoImproveQuality_value"));
            }
            if (!jjTools.getParameter(request, "config_sendPlantoManager_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_sendPlantoManager_value"));
            }
            if (!jjTools.getParameter(request, "config_communicateofPlanSteps_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_communicateofPlanSteps_value"));
            }
            if (!jjTools.getParameter(request, "config_sendPlantoCommittee_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_sendPlantoCommittee_value"));
            }
            if (!jjTools.getParameter(request, "config_requestEditingthePlan_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_requestEditingthePlan_value"));
            }
            if (!jjTools.getParameter(request, "config_offerFormtotheCommittee_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_offerFormtotheCommittee_value"));
            }
            if (!jjTools.getParameter(request, "config_reminderDayBeforeStepsStartDate_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_reminderDayBeforeStepsStartDate_value"));
            }
            if (!jjTools.getParameter(request, "config_reminderDayBeforeStepsEndDate_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_reminderDayBeforeStepsEndDate_value"));
            }
            if (!jjTools.getParameter(request, "config_reminderDayBeforeApprovedStartDate_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_reminderDayBeforeApprovedStartDate_value"));
            }
            if (!jjTools.getParameter(request, "config_reminderDayBeforeApprovedEndDate_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_reminderDayBeforeApprovedEndDate_value"));
            }

            if (!jjTools.getParameter(request, "config_reminderDayBeforeNextSessions_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_reminderDayBeforeNextSessions_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailModuleCommittee_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailModuleCommittee_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsModuleCommittee_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsModuleCommittee_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsModuleDocumentary_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsModuleDocumentary_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailModuleDocumentary_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailModuleDocumentary_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsModulePlans_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsModulePlans_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailModulePlans_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailModulePlans_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsModuleIndicators_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsModuleIndicators_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailModuleIndicators_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailModuleIndicators_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsLogin_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsModuleLogin_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailLogin_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailLogin_value"));
            }
            if (!jjTools.getParameter(request, "config_activeSmsChangeProfile_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeSmsChangeProfile_value"));
            }
            if (!jjTools.getParameter(request, "config_activeEmailChangeProfile_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_activeEmailChangeProfile_value"));
            }
            if (!jjTools.getParameter(request, "config_loginMobileWithToken_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_loginMobileWithToken_value"));
            }
            if (!jjTools.getParameter(request, "config_loginMobileWithTokenJustUniqDevice_value").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_loginMobileWithTokenJustUniqDevice_value"));
            }
            if (!jjTools.getParameter(request, "config_exchange_unite").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_exchange_unite"));
            }
            if (!jjTools.getParameter(request, "config_companyName").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_companyName"));
            }
            if (!jjTools.getParameter(request, "config_nationalCode").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_nationalCode"));
            }
            if (!jjTools.getParameter(request, "config_economicCode").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_economicCode"));
            }
            if (!jjTools.getParameter(request, "config_addressCompany").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_addressCompany"));
            }
            if (!jjTools.getParameter(request, "config_tellCompany").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_tellCompany"));
            }
            if (!jjTools.getParameter(request, "config_zipcodeCompany").equals("")) {
                Tice_config.setNewConfiguration(db, ConfigRow.get(0).get(_config_Name).toString(), jjTools.getParameter(request, "config_zipcodeCompany"));
            }

//            Server.outPrinter(request, response, Js.modal("تغییرات انجام شد", "پیام سامانه"));
            return "";

        } catch (Exception ex) {
            Server.outPrinter(request, response, Server.ErrorHandler(ex));
            return "";
        }
    }
}
