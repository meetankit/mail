package com.msl.mo.delegate;

import com.msl.mo.POD.service.PODService;
import com.msl.mo.UploadExcel.service.UploadExcelService;
import com.msl.mo.audit.service.AuditService;
import com.msl.mo.dashboard.service.DashboardService;
import com.msl.mo.dialoguescreen.service.DialogueScreenService;
import com.msl.mo.finscope.service.FinScopeService;
import com.msl.mo.mail.service.MailService;
import com.msl.mo.messagequeue.service.MessageQueueService;
import com.msl.mo.supplier.service.SupplierService;
import com.msl.mo.supplierAddAddress.service.supplierAddAddressService;
import com.msl.mo.sysParam.service.sysParamService;
import com.msl.mo.user.service.UserService;
import com.msl.mo.utility.MOnlineService;
import com.msl.mo.utility.ServiceLocator;
import com.msl.mo.workorder.service.AdditionalCostService;
import com.msl.mo.workorder.service.NonConformanceService;
import com.msl.mo.workorder.service.SortSettingService;
import com.msl.mo.workorder.service.WorkOrderService;

public class Delegate{
	
	private WorkOrderService workOrderService;
	
	private MessageQueueService messageQueueService;
	
	private NonConformanceService nonConService;
	
	private UserService userService;
	
	private AuditService auditService;
	
	private AdditionalCostService additionalcostservice;
	
	private SortSettingService sortSettingService;
	
	//private ExportExcelService exportExcelService;
	
	private FinScopeService finScopeService;
	
	private DialogueScreenService dialogueScreenService;
	
	private DashboardService dashboardService;
	
	private MailService mailService;
	
	private SupplierService supplierService;
	
	private UploadExcelService uploadExcelService;
	
	private PODService podService;
	
	private supplierAddAddressService supplieraddAddressService;
	
	private sysParamService sysparamservice;
		
	public WorkOrderService getWorkOrderService() throws Exception{
		workOrderService =(WorkOrderService) ServiceLocator.lookupService(MOnlineService.WO_SERVICE,
				MOnlineService.WO_SERVICE_BEAN);	
		return workOrderService;
	}
	public DashboardService getDashboardService() throws Exception{
		dashboardService =(DashboardService) ServiceLocator.lookupService(MOnlineService.DASHBOARD_SERVICE,
				MOnlineService.DASHBOARD_SERVICE_BEAN);	
		return dashboardService;
	}
	public MailService getMailService() throws Exception{
		mailService =(MailService) ServiceLocator.lookupService(MOnlineService.MAILS_SERVICE,
				MOnlineService.MAILS_SERVICE_BEAN);	
		return mailService;
	}
	public MessageQueueService getMessageQueueService() throws Exception{
		messageQueueService =(MessageQueueService) ServiceLocator.lookupService(MOnlineService.MQ_SERVICE,
				MOnlineService.MQ_SERVICE_BEAN);	
		return messageQueueService;
	}
	public NonConformanceService getNonConService() throws Exception{
		nonConService =(NonConformanceService) ServiceLocator.lookupService(MOnlineService.NON_CON_SERVICE,
				MOnlineService.NON_CON_SERVICE_BEAN);	
		return nonConService;
	}
	
	public UserService getUserService() throws Exception{
		userService =(UserService) ServiceLocator.lookupService(MOnlineService.UM_SERVICE,
				MOnlineService.UM_SERVICE_BEAN);	
		return userService;
	}
	
	 public AuditService getAuditService() throws Exception{
		auditService =(AuditService) ServiceLocator.lookupService(MOnlineService.AUDIT_SERVICE,
				MOnlineService.AUDIT_SERVICE_BEAN);	
		return auditService;
	}
	 
	public SortSettingService getSortSettingService() throws Exception{
		sortSettingService =(SortSettingService) ServiceLocator.lookupService(MOnlineService.SORT_SETTING_SERVICE,
				MOnlineService.SORT_SETTING_BEAN);	
		return sortSettingService;
	}
	
	public AdditionalCostService getAdditionalCostService() throws Exception{
		additionalcostservice =(AdditionalCostService) ServiceLocator.lookupService(MOnlineService.ADDITIONAL_COST_SERVICE,
				MOnlineService.ADDITIONAL_COST_BEAN);	
		return additionalcostservice;
	}
	
/*	public ExportExcelService getExportExcelService() throws Exception{
		exportExcelService =(ExportExcelService) ServiceLocator.lookupService(MOnlineService.EXPORT_EXCEL_SERVICE,
				MOnlineService.EXPORT_EXCEL_BEAN);	
		return exportExcelService;
	}
*/	
	public FinScopeService getFinScopeService() throws Exception{
		finScopeService =(FinScopeService) ServiceLocator.lookupService(MOnlineService.FIN_SERVICE,
				MOnlineService.FIN_SERVICE_BEAN);	
		return finScopeService;
	}
	
	public DialogueScreenService getDialogueScreenService() throws Exception{
		System.out.println("inside delegate");
		dialogueScreenService =(DialogueScreenService) ServiceLocator.lookupService(MOnlineService.DIA_SERVICE,
				MOnlineService.DIA_SERVICE_BEAN);	
		return dialogueScreenService;
	}
	
	public SupplierService getSupplierService() throws Exception{
		supplierService =(SupplierService) ServiceLocator.lookupService(MOnlineService.SUPP_SERVICE,
				MOnlineService.SUPP_SERVICE_BEAN);	
		return supplierService;
	}
	
	public UploadExcelService getUploadExcelService() throws Exception{
		uploadExcelService =(UploadExcelService) ServiceLocator.lookupService(MOnlineService.UPLOAD_EXCEL_SERVICE,
				MOnlineService.UPLOAD_EXCEL_BEAN);	
		return uploadExcelService;
	}
	
	public PODService getPODService() throws Exception{
		podService =(PODService) ServiceLocator.lookupService(MOnlineService.POD_SERVICE,
				MOnlineService.POD_BEAN);	
		return podService;
	}
	
	public supplierAddAddressService getSupplierAddAddressService() throws Exception
	{
		supplieraddAddressService=(supplierAddAddressService) ServiceLocator.lookupService(MOnlineService.SUPP_ADD_ADDRESS_SERVICE,
				MOnlineService.SUPP_ADD_ADDRESS_SERVICE_BEAN);
		return supplieraddAddressService;
	}
	
	public sysParamService getSysParamService() throws Exception{
		sysparamservice =(sysParamService) ServiceLocator.lookupService(MOnlineService.SYS_PARAM_SERVICE,
				MOnlineService.SYS_PARAM_SERVICE_BEAN);	
		return sysparamservice;
	}

}