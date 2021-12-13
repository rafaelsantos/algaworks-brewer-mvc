var Brewer = Brewer || {};

Brewer.TypeSave = (function() {

	function TypeSave() {
		this.modal = $('#typeModal');
		this.buttonSave = this.modal.find('.js-modal-save-type');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.input = $('#typeName');
		this.errorDiv = $('.js-message-save-type');
	}
	
	TypeSave.prototype.start = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.buttonSave.on('click', onButtonSaveClick.bind(this));
	}
	
	function onModalShow() {
		this.input.focus();
	}
	
	function onModalClose() {
		this.input.val('');
		this.errorDiv.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onButtonSaveClick() {
		var typeName = this.input.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ name: typeName }),
			error: onErrorSavingType.bind(this),
			success: onTypeSaved.bind(this)
		});
	}
	
	function onErrorSavingType(object) {
		var error = object.responseText;
		
		this.errorDiv.removeClass('hidden');
		this.errorDiv.html('<span>' + error + '</span>');
		
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onTypeSaved(type) {
		var typeSelect = $('#type');
		typeSelect.append('<option value=' + type.code + '>' + type.name + '</option>');
		typeSelect.val(type.code);
		this.modal.modal('hide');
	}
	
	return TypeSave;
}());

$(function() {
	var typeSave = new Brewer.TypeSave();
	typeSave.start();
});