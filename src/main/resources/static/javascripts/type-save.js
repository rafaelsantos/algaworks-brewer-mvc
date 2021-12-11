$(function() {
	var modal = $('#typeModal');
	
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() });
	
	var buttonSave = modal.find('.js-modal-save-type');
	buttonSave.on('click', onButtonSaveClick);
	
	var url = form.attr('action');
	var input = $('#typeName');
	var errorDiv = $('.js-message-save-type');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose);
	
	function onModalShow() {
		input.focus();
	}
	
	function onModalClose() {
		input.val('');
		errorDiv.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onButtonSaveClick() {
		var typeName = input.val().trim();
		
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ name: typeName }),
			error: onErrorSavingType,
			success: onTypeSaved
		});
	}
	
	function onErrorSavingType(object) {
		var error = object.responseText;
		
		errorDiv.removeClass('hidden');
		errorDiv.html('<span>' + error + '</span>');
		
		form.find('.form-group').addClass('has-error');
	}
	
	function onTypeSaved(type) {
		var typeSelect = $('#type');
		typeSelect.append('<option value=' + type.code + '>' + type.name + '</option>');
		typeSelect.val(type.code);
		modal.modal('hide');
	}
});