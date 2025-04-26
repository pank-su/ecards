package su.pank.ecards.ui.start


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

val nicknames = listOf(
    "Железный Барон",
    "Промышленный Леопард",
    "Монопольный Магнат",
    "Стальной Кронпринц",
    "Заводской Цезарь",
    "Король Конвейеров",
    "Машиностроительный Меценат",
    "Император Индустрии",
    "Магнат Манхэттена",
    "Титан Труда",
    "Профессор Профита",
    "Царь Цехов",
    "Гранд Гараж",
    "Фабричный Феникс",
    "ПромоПатриарх",
    "Монополист Максимус",
    "Атомный Аристократ",
    "Король Контроля",
    "Династия Деталей",
    "Рабочий Рейнджер",
    "Гений Гигантов",
    "Промышленный Пророк",
    "Собственник Сборочных Линий",
    "Барон Балансов",
    "Шеф Шестерёнок",
    "Монополийный Мастер",
    "Индустриальный Инквизитор",
    "Повелитель Производства",
    "Лорд Литейного Завода",
    "Генерал Гигантфабрик",
    "Радикс Ресурсов",
    "Магнат Механоидов",
    "Царь Цикла",
    "Туз Технологий",
    "Капитан Котлов",
    "Султан Складов",
    "Архитектор Автоматизации",
    "Джет Пульт Промышленности",
    "ПромоПром Профи",
    "Алхимик Акций",
    "Нефтяной Нострадамус",
    "Повелитель Прибылей"
)


sealed interface StartState{
    data object Find: StartState
    data class NameEdited(var name: String): StartState
}

class StartViewModel: ViewModel() {

    private val _state = MutableStateFlow<StartState>(StartState.NameEdited(nicknames.random()))
    val state = _state.asStateFlow()

    fun onNameSet(name: String){
        _state.value = StartState.NameEdited(name)
    }
}