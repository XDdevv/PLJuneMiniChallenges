package zed.rainxch.pljuneminichallenges.gift_memory_match.presentation.static_

import androidx.compose.ui.graphics.Color

data class GiftItem(
    val recipient: String,
    val gift: String,
    val colorName: String,
    val color: Color,
)

val giftsList = listOf(
    GiftItem("Lily", "Watering can", "Leaf Green", LeafGreen),
    GiftItem("Chip", "Cookie tin", "Caramel Brown", CaramelBrown),
    GiftItem("Rose", "Scented candle", "Rose Pink", RosePink),
    GiftItem("Buzz", "Toy drone", "Electric Blue", ElectricBlue),
    GiftItem("Dot", "Polka mug", "Cherry Red", CherryRed),
    GiftItem("Max", "Dumbbell keychain", "Steel Gray", SteelGray),
    GiftItem("Finn", "Goldfish plush", "Tangerine", Tangerine),
    GiftItem("Belle", "Hand mirror", "Lavender", Lavender),
    GiftItem("Art", "Paint palette", "Canvas Beige", CanvasBeige),
    GiftItem("Mel", "Honey jar", "Honey Gold", HoneyGold),
    GiftItem("Noel", "Snow globe", "Icy Blue", IcyBlue),
    GiftItem("Sunny", "Sun hat", "Lemon Yellow", LemonYellow),
    GiftItem("Ivy", "Hanging plant", "Forest Green", ForestGreen),
    GiftItem("Ginger", "Spice rack", "Cinnamon", Cinnamon),
    GiftItem("Rocky", "Pet rock", "Granite Gray", GraniteGray),
    GiftItem("Coco", "Hot cocoa mix", "Cocoa Brown", CocoaBrown),
    GiftItem("Bree", "Wind chime", "Sky Blue", SkyBlue),
    GiftItem("Joy", "Confetti popper", "Party Pink", PartyPink),
    GiftItem("Ash", "Campfire candle", "Charcoal Gray", CharcoalGray),
    GiftItem("Brock", "Onix figure", "Boulder Gray", BoulderGray)
)