package ee.hotel.booking

import geb.spock.GebSpec

import pages.HotelBookingPage
import spock.lang.Unroll

class HotelBookingTestSpec extends GebSpec {

    @Unroll
    def "New bookings can be saved"() {
        given:
        HotelBookingPage hotelBookingPage = to HotelBookingPage
        // Below would need refactoring to avoid breaking another test when running in parallel
        hotelBookingPage.deleteAllBookings()

        when:
        hotelBookingPage.makeSuccessfulBooking(
                firstName, surname, price, deposit, checkInDate, checkOutDate)

        then:
        assert hotelBookingPage.bookingWasCorrectlySaved(
                firstName, surname, price, deposit, checkInDate, checkOutDate)

        where:
        firstName   | surname   | price     | deposit   | checkInDate   | checkOutDate
        'John'      | 'Smith'   | '123'     | 'true'    | '2019-02-03'  | '2019-02-10'
        'A fairly long-name with chars'      | 'A fairly long-name with chars'   | '12345678'     | 'false'    | '2020-01-01'  | '2020-12-31'
    }

    @Unroll
    def "Invalid bookings can not be saved"() {
        given:
        HotelBookingPage hotelBookingPage = to HotelBookingPage
        // Below would need refactoring to avoid breaking another test when running in parallel
        hotelBookingPage.deleteAllBookings()

        when:
        hotelBookingPage.attemptInvalidBooking(
                firstName, surname, price, deposit, checkInDate, checkOutDate)

        then:
        assert hotelBookingPage.bookingTableIsEmpty()
        // An extra assertion could be added to verify error messages once bug is fixed
        // but this might be sufficiently covered at unit level

        where:
        firstName       | surname       | price     | deposit   | checkInDate   | checkOutDate
        ''              | ''            | ''        | ''        | ''            | ''
        '!@#$%^&*()'    | '!@#$%^&*()'  | '123'     | 'true'    | '2019-02-03'  | '2019-02-04'

//         These scenarios would be re-introduced after correct validation was implemented
//        '!@#$%^&*()'    | 'def'         | '123'     | 'true'    | '2019-02-03'  | '2019-02-04'
//        'abc'           | '!@#$%^&*()'  | '132'     | 'true'    | '2019-02-03'  | '2019-02-04'
//        'abc'           | 'def'         | 'abc'     | 'true'    | '2019-02-03'  | '2019-02-04'
//        'abc'           | 'def'         | '£12.00'  | 'true'    | '2019-02-03'  | '2019-02-04'
//        'abc'           | 'def'         | '123'     | 'true'    | '01-01-2001'  | '2019-02-04'
//        'abc'           | 'def'         | '123'     | 'true'    | '2019-02-03'  | '01-01-2001'
    }

    def "An existing booking can be deleted"() {
        given:
        HotelBookingPage hotelBookingPage = to HotelBookingPage
        hotelBookingPage.makeSuccessfulBooking(
                'A', 'B', '123', 'true', '2019-02-03', '2019-02-04')

        when:
        // Below would need refactoring to avoid breaking another test when running in parallel
        hotelBookingPage.deleteAllBookings()

        then:
        assert hotelBookingPage.bookingTableIsEmpty()
    }

//    def "Selecting a date with the date selector populates the relevant date field"() {
//        // This would be a test to verify the date field populated the input fields as expected
//    }

}
