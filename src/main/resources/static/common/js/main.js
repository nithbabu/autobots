/**
 * 
 */
 
jQuery(document).ready(function($){

	$('select').select2();

	$("#vehicletype").on("change", function() {
		console.log($(this).val());
		if($(this).val() == "bike") {
			$("#numofdoors").closest(".form-group").attr("hidden", "hidden");
			$("#carryingcapacity").closest(".form-group").attr("hidden", "hidden");
			$("#maxleanangle").closest(".form-group").removeAttr("hidden");
		} else if($(this).val() == "car") {
			$("#numofdoors").closest(".form-group").removeAttr("hidden");
			$("#maxleanangle").closest(".form-group").attr("hidden", "hidden");
			$("#carryingcapacity").closest(".form-group").attr("hidden", "hidden");
		} else if($(this).val() == "lorry") {
			$("#maxleanangle").closest(".form-group").attr("hidden", "hidden");
			$("#carryingcapacity").closest(".form-group").removeAttr("hidden");			
		}
	});
});