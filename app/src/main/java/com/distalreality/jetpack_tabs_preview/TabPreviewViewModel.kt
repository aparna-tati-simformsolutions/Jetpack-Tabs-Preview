package com.distalreality.jetpack_tabs_preview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TabPreviewViewModel : ViewModel() {
    private val _selectedPerson = mutableStateOf<Triple<Int, Float, Float>?>(null)
    val selectedPerson: State<Triple<Int, Float, Float>?> = _selectedPerson

    private val _lastElement =  mutableStateOf<Person?>(null)
    val lastElement: State<Person?> = _lastElement

    fun selectPerson(person: Person, adjustedWidth: Float, adjustedHeight: Float) {
        _lastElement.value = person
        _selectedPerson.value = Triple(person.id, adjustedWidth, adjustedHeight)
    }

    fun clearSelectedPerson() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(350)
            _selectedPerson.value = null
        }
    }

    fun getPerson(id: Int): Person = getPersonList().find { it.id == id }!!

    fun getPersonList() = listOf(
        Person(0,R.drawable.avatar_1, name = "Name1", profession = "SE1"),
        Person(1,R.drawable.avatar_2, name = "Name2", profession = "SE2"),
        Person(2,R.drawable.avatar_3, name = "Name3", profession = "SE3"),
        Person(3,R.drawable.avatar_4, name = "Name4", profession = "SE4"),
        Person(4,R.drawable.avatar_5, name = "Name5", profession = "SE5"),
        Person(5,R.drawable.avatar_6, name = "Name6", profession = "SE6"),
        Person(6,R.drawable.avatar_7, name = "Name7", profession = "SE7"),
        Person(7,R.drawable.avatar_8, name = "Name8", profession = "SE8"),
        Person(8,R.drawable.avatar_9, name = "Name9", profession = "SE9"),
        Person(9,R.drawable.avatar_10, name = "Name10", profession = "SE10"),
        Person(10,R.drawable.avatar_11, name = "Name11", profession = "SE11"),
        Person(11,R.drawable.avatar_12, name = "Name12", profession = "SE12"),
        Person(12,R.drawable.avatar_13, name = "Name13", profession = "SE13"),
        Person(13,R.drawable.avatar_14, name = "Name14", profession = "SE14")
    )
}
