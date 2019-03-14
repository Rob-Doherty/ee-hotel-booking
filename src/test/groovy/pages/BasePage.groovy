package pages

import geb.Page

class BasePage extends Page {

    static content = {
        pageHeading             { $("div.jumbotron h1") }
        bookingsTable           { $("#bookings") }
    }

}
