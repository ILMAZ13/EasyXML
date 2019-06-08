package ru.kpfu.easyxml.modules.utils

object IdHelper {
    private val idSet = HashSet<String>()

    fun getUniqueId(name: String, prefix: String): String {
        var newId = uniName(name, prefix)
        var i = 0
        while (idSet.contains(newId)) {
            i++
            newId = uniName(name, prefix, i)
        }
        idSet.add(newId)
        return newId
    }

    private fun uniName(name: String, prefix: String) =
            "${prefix}_${name.toLowerCase().replace(' ', '_', true)}"

    private fun uniName(name: String, prefix: String, postfix: Int) =
            "${prefix}_${name.toLowerCase().replace(' ', '_', true)}$postfix"
}
