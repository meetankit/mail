package com.msl.mo.utility;


public interface MOnlineService {
	
	
	String PROVIDER_URL ="java.naming.provider.url";
	String HOSTNAME ="t3://localhost:7001";
	String NAMING_FACTORY = "java.naming.factory.initial";
	String CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	
	/**
	 * Add two entries  regarding to each bean here. Format of entries
	 *  String  <BEANCLASS_BEAN>="<MappedName#complete class path of the bean>";
	 *  String  <BEANCLASS>="<Interface Name>";
	 */
	String WO_SERVICE_BEAN = "MOnline/WorkOrderService/remote-com.msl.mo.workorder.service.WorkOrderService";
	String WO_SERVICE = "WorkOrderService";
	
	String MQ_SERVICE_BEAN = "MOnline/MessageQueueService/remote-com.msl.mo.messagequeue.service.MessageQueueService";
	String MQ_SERVICE = "MessageQueueService";
	
	String ADDITIONAL_COST_BEAN = "MOnline/AdditionalCostService/remote-com.msl.mo.workorder.service.AdditionalCostService";
	String ADDITIONAL_COST_SERVICE = "AdditionalCostService";
	
	String NON_CON_SERVICE_BEAN = "MOnline/NonConformanceService/remote-com.msl.mo.workorder.service.NonConformanceService";
	String NON_CON_SERVICE = "NonConformanceService";
	
	String UM_SERVICE_BEAN = "MOnline/UserService/remote-com.msl.mo.user.service.UserService";
	String UM_SERVICE = "UserService";
	
	String SORT_SETTING_BEAN = "MOnline/SortSettingService/remote-com.msl.mo.workorder.service.SortSettingService";
	String SORT_SETTING_SERVICE = "SortSettingService";
	
	String SUPP_SERVICE_BEAN = "MOnline/SupplierService/remote-com.msl.mo.supplier.service.SupplierService";
	String SUPP_SERVICE = "SupplierService";
	
	String EXPORT_EXCEL_BEAN = "MOnline/ExportExcelService/remote-com.msl.mo.exportexcel.Service.ExportExcelService";
	String EXPORT_EXCEL_SERVICE = "ExportExcelService";
	
	String DIA_SERVICE_BEAN="MOnline/DialogueScreenService/remote-com.msl.mo.dialoguescreen.service.DialogueScreenService";
	String DIA_SERVICE="DialogueScreenService";
	
	String DASHBOARD_SERVICE_BEAN="MOnline/DashboardService/remote-com.msl.mo.dashboard.service.DashboardService";
	String DASHBOARD_SERVICE="DashboardService";
	
	String MAILS_SERVICE_BEAN="MOnline/MailService/remote-com.msl.mo.mail.service.MailService";
	String MAILS_SERVICE="MailService";
	
	String FIN_SERVICE_BEAN = "MOnline/FinScopeService/remote-com.msl.mo.finscope.service.FinScopeService";
	String FIN_SERVICE = "FinScopeService";
	
	String PARSER_SERVICE_BEAN="MOnline/ParserService/remote-com.msl.mo.parser.service.ParserService";
	String PARSER_SERVICE="ParserService";
	
	String AUDIT_SERVICE_BEAN="MOnline/AuditService/remote-com.msl.mo.audit.service.AuditService";
	String AUDIT_SERVICE="AuditService";	

	String POD_BEAN="MOnline/PODService/remote-com.msl.mo.POD.service.PODService";
	String POD_SERVICE="PODService";
	
	String UPLOAD_EXCEL_BEAN="MOnline/UploadExcelService/remote-com.msl.mo.UploadExcel.service.UploadExcelService";
	String UPLOAD_EXCEL_SERVICE="UploadExcelService";
	
	String SUPP_ADD_ADDRESS_SERVICE_BEAN="MOnline/supplierAddAddressService/remote-com.msl.mo.supplierAddAddress.service.supplierAddAddressService";
	String SUPP_ADD_ADDRESS_SERVICE="supplierAddAddressService";
	
	String SYS_PARAM_SERVICE_BEAN = "MOnline/sysParamService/remote-com.msl.mo.sysParam.service.sysParamService";
	String SYS_PARAM_SERVICE = "sysParamService";

}
