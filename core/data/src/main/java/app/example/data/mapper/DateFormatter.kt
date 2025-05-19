package app.example.data.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateFormatter {

    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

    fun formatDate(dateString: String): String {
        return try {
            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (e: Exception) {
            dateString
        }
    }
}