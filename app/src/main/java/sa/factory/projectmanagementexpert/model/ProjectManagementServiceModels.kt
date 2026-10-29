package sa.factory.projectmanagementexpert.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectTask(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class ProjectManagementService(
    val requestId: String,
    val items: List<ProjectTask>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ProjectManagementServiceStatus = ProjectManagementServiceStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ProjectManagementServiceStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
