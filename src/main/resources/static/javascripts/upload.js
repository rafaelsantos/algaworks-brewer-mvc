var Brewer = Brewer || {};

Brewer.UploadImage = (function() {

	function UploadImage() {
		this.imageName = $('input[name=image]');
		this.contentType = $('input[name=contentType]');
		this.source = $('#beer-image').html();
		this.container = $('.js-image');
		this.uploadDrop = $('#upload-drop');
		
		this.template = Handlebars.compile(this.source);
	}
	
	UploadImage.prototype.build = function() {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: this.container.data('url-images'),
			complete: onUploadComplete.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	function onUploadComplete(response) {
		this.imageName.val(response.name);
		this.contentType.val(response.contentType);
		
		this.uploadDrop.addClass('hidden');
		this.html = this.template({name: response.name});
		this.container.append(this.html);
		
		$('.js-remove-image').on('click', onRemoveImage.bind(this));
	}
	
	function onRemoveImage() {
		$('.js-image-beer').remove();
		this.uploadDrop.removeClass('hidden');
		this.imageName.val('');
		this.contentType.val('');
	}
	
	return UploadImage;
})();

$(function() {
	var uploadImage = new Brewer.UploadImage();
	uploadImage.build();
});