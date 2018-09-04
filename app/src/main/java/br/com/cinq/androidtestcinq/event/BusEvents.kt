package br.com.cinq.androidtestcinq.event

import br.com.cinq.androidtestcinq.persistence.User


data class EditUserEvent(val user: User)

data class DeleteUserEvent(val user: User)