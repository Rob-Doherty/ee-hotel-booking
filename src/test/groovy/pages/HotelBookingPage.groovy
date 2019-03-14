package pages

import modules.BookingRowModule
import modules.FormRowModule

class HotelBookingPage extends BasePage {

    static at = {
        waitFor { title == "Hotel booking form" }
        waitFor { pageHeading.text() == "Hotel booking form" }
        waitFor { formRow.firstNameInput.displayed }
    }

    static content = {
        formRow                 { module(FormRowModule) }
        bookingRows             { bookingsTable.$('div.row').moduleList(BookingRowModule) }
    }

    void makeBooking(
            String firstName,
            String surname,
            String price,
            String deposit,
            String checkInDate,
            String checkOutDate
    ) {
        if (firstName) { formRow.firstNameInput.value(firstName) }
        if (surname) { formRow.lastNameInput.value(surname) }
        if (price) { formRow.totalPriceInput.value(price) }
        if (deposit) { formRow.depositPaidSelect.value(deposit) }
        if (checkInDate) { formRow.checkInInput.value(checkInDate) }
        if (checkOutDate) { formRow.checkOutInput.value(checkOutDate) }

        formRow.saveButton.click()
    }

    HotelBookingPage makeSuccessfulBooking(
            String firstName,
            String surname,
            String price,
            String deposit,
            String checkInDate,
            String checkOutDate
    ) {
        browser.at HotelBookingPage

        makeBooking(firstName, surname, price, deposit, checkInDate, checkOutDate)
        waitFor { bookingTableNotEmpty() }
        browser.at HotelBookingPage
    }

    HotelBookingPage attemptInvalidBooking(
            String firstName,
            String surname,
            String price,
            String deposit,
            String checkInDate,
            String checkOutDate
    ) {
        browser.at HotelBookingPage

        makeBooking(firstName, surname, price, deposit, checkInDate, checkOutDate)

        // This would be refactored out once the correct error handling was in place
        Thread.sleep(3000)

        browser.at HotelBookingPage
    }

    boolean bookingWasCorrectlySaved(
            String firstName,
            String surname,
            String price,
            String deposit,
            String checkInDate,
            String checkOutDate
    ) {
        browser.at HotelBookingPage

        return firstName == bookingRows[1].firstNameField &&
                surname == bookingRows[1].lastNameField &&
                price == bookingRows[1].totalPriceField &&
                deposit == bookingRows[1].depositPaidField &&
                checkInDate == bookingRows[1].checkInDate &&
                checkOutDate == bookingRows[1].checkOutDate
    }

    boolean bookingTableNotEmpty() {
        bookingRows.size() > 1
    }

    boolean bookingTableIsEmpty() {
        bookingRows.size() >= 1
    }

    void deleteAllBookings() {
        if (bookingTableNotEmpty()) {
            bookingRows[1..bookingRows.size()-1].each { it.deleteButton.click() }
        }
        assert bookingTableIsEmpty()
    }

}
