package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.party_timeline

import androidx.compose.runtime.currentComposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zed.rainxch.pljuneminichallenges.core.presentation.utils.StringResourceProvider
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.Event
import zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model.toEvent
import kotlin.random.Random

data class PartyTimelineUiState(
    val eventList: List<Event> = emptyList(),
    val currentSelectedGuest: Event? = null
)

class PartyTimelineViewModel(
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {
    private val _state = MutableStateFlow(PartyTimelineUiState())
    val state = _state
        .onStart {
            initEventList()
            _state.update { it.copy(currentSelectedGuest = it.eventList.first()) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = PartyTimelineUiState()
        )

    fun initEventList() {
        val timeLines = mutableListOf<String>()
        val eventTitles = stringResourceProvider.getEventList()
        repeat(eventTitles.size) {
            timeLines.add("${Random.nextInt(12, 23)}:${Random.nextInt(0, 59).toString().padEnd(2, '0')}")
        }
        val descriptionsList = listOf(
            "New Year's Day marks the beginning of the year with celebrations, fireworks, and resolutions for a fresh start.",
            "Christmas is a joyful holiday celebrated with gift-giving, decorations, and festive meals with family and friends.",
            "Eid al-Fitr is a significant Islamic holiday marking the end of Ramadan, celebrated with prayers, feasts, and charity.",
            "Diwali, the Festival of Lights, symbolizes the victory of light over darkness and is celebrated with lamps, sweets, and fireworks.",
            "Thanksgiving is a day for expressing gratitude, enjoying a hearty meal, and spending time with loved ones.",
            "Independence Day commemorates a nation's freedom and is often celebrated with parades, flags, and fireworks.",
            "Hanukkah is an eight-day Jewish festival featuring menorah lighting, traditional foods, and games like dreidel.",
            "Easter is a Christian holiday celebrating the resurrection of Jesus, often associated with egg hunts and springtime festivities.",
            "Chinese New Year is a vibrant celebration marked by dragon dances, red decorations, and family reunions.",
            "Halloween is a fun holiday where people dress up in costumes, carve pumpkins, and go trick-or-treating."
        )

        val eventsList = eventTitles.map { it.toEvent(timeLines, descriptionsList) }
        _state.update { it.copy(eventList = eventsList) }
    }

    fun onAction(action: EventTimelineActions) {
        when (action) {
            is EventTimelineActions.OnEventSelected -> {
                _state.update { it.copy(currentSelectedGuest = action.event) }
            }
        }
    }

}