import android.os.Handler
import android.os.Looper

data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: String,
    val reported_at: String
)

// Define severity levels
enum class Severity {
    LOW,
    MEDIUM,
    HIGH
} 