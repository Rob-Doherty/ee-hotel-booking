package modules

import geb.Module

class BookingRowModule extends Module {

    static content = {
        firstNameField      { $('div',0).text() }
        lastNameField       { $('div',1).text() }
        totalPriceField     { $('div',2).text() }
        depositPaidField    { $('div',3).text() }
        checkInDate         { $('div',4).text() }
        checkOutDate        { $('div',5).text() }

        deleteButton        { $("input[type='button'][value='Delete']") }
    }

}
