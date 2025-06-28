package zed.rainxch.pljuneminichallenges.party_host_dashboard.presentation.model

data class Gift(
    val prize: String,
    val receiver: String
)

fun String.toGift(
    receiverList: List<String>
) : Gift {
    return Gift(
        prize = this,
        receiver = receiverList.random()
    )
}