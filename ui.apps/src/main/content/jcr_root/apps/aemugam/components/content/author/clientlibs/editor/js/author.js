/* global jQuery, Coral */
(function($, Coral) {
    "use strict";

    console.log(" --------CLIENTLIBS LOADED------- ");

    var registry = $(window).adaptTo("foundation-registry");

    // To validate max and min items for multifield
    registry.register("foundation.validation.validator", {
        selector: "[data-validation=ugam-multifield]",
        validate: function(element) {
            var el = $(element);
            let max=el.data("max-items");
            let min=el.data("min-items");
            let items=el.children("coral-multifield-item").length;
            let domitems=el.children("coral-multifield-item");
            console.log("{} : {} :{} ",max,min,items);
            if(items>max){
              /* Use below line if you don't want to add item in multifield more than max limit */
              //domitems.last().remove();
              return "You can add maximum "+max+" books. You are trying to add "+items+"th book."
            }
            if(items<min){
                return "You must add minimum "+min+" books."
            }
        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=ugam-firstname-validation]",
        validate: function(element) {
            let el = $(element);
            let pattern=/[0-9a-z]/;
            let value=el.val();
            if(pattern.test(value)){
               return "Please add only Upper Case Letters in First name";
            }

        }
    });

   
})(jQuery, Coral);
