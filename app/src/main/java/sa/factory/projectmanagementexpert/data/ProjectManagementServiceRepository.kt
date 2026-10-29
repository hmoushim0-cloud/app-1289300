package sa.factory.projectmanagementexpert.data

import sa.factory.projectmanagementexpert.model.ProjectManagementService
import sa.factory.projectmanagementexpert.model.ProjectTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ProjectManagementServiceRepository {
    fun getAvailableCatalog(): Flow<List<ProjectTask>>
    fun getActiveRequest(): Flow<ProjectManagementService?>
    suspend fun submitRequest(request: ProjectManagementService): Result<String>
}

class InMemoryProjectManagementServiceRepository : ProjectManagementServiceRepository {
    private val _catalog = MutableStateFlow<List<ProjectTask>>(listOf(
        ProjectTask("1", "خدمة إدارة المشاريع الشاملة", "توفير خبير لإدارة جميع جوانب المشروع، بما في ذلك الاستفسارات الفنية والوثائق.", 5000L, "verified"),
        ProjectTask("2", "خدمة اعتماد المواد والفحص", "تسهيل عملية اعتماد المواد وفحصها لضمان الجودة.", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<ProjectManagementService?>(null)

    override fun getAvailableCatalog(): Flow<List<ProjectTask>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<ProjectManagementService?> = _active.asStateFlow()

    override suspend fun submitRequest(request: ProjectManagementService): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
