package sa.factory.projectmanagementexpert.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.projectmanagementexpert.data.ProjectManagementServiceRepository
import sa.factory.projectmanagementexpert.model.ProjectManagementService
import sa.factory.projectmanagementexpert.model.ProjectTask
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProjectManagementServiceViewModel(
    private val repository: ProjectManagementServiceRepository
) : ViewModel() {
    val catalog: StateFlow<List<ProjectTask>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<ProjectManagementService?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: ProjectManagementService) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
