package modules

import geb.Module

class FormRowModule extends Module {

    static content = {
        formElement             { $("#form") }
        firstNameInput          { $("#firstname") }
        lastNameInput           { $("#lastname") }
        totalPriceInput         { $("#totalprice") }
        depositPaidSelect       { $("#depositpaid") }
        checkInInput            { $("#checkin") }
        checkOutInput           { $("#checkout") }

        saveButton              { $("input[type='button'][value=' Save ']") }
    }

}