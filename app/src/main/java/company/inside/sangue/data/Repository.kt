package company.inside.sangue.data

interface Repository<T> {

    fun add(item:T)

    fun update(item: T)

    fun remove(item: T)

    fun findAll():List<T>
}