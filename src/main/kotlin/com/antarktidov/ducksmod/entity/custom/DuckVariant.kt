package com.antarktidov.ducksmod.entity.custom

enum class DuckVariant(val id: Int) {
    WHITE(0),
    MALE_MALLARD(1),
    FEMALE_MALLARD(2),
    MUSCOVY(3),
    MANDARIN(4);

    companion object {
        private val BY_ID: Array<DuckVariant> = entries.sortedBy { it.id }.toTypedArray()

        @JvmStatic
        fun byId(id: Int): DuckVariant = BY_ID[id % BY_ID.size]
    }
}
