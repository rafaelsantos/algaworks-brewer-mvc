var Brewer = Brewer || {};

Brewer.MaskMoney = (function() {
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	
	return MaskMoney;
}());

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
});