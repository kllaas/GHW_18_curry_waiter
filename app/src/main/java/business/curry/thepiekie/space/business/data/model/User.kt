package business.curry.thepiekie.space.business.data.model

data class User(
    private val id: String,
    private val email: String,
    private val name: String,
    private val surname: String,
    private val birthday: Long,
    private val registered: Long
)