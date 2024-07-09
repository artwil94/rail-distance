package com.example.raildistance.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raildistance.R
import com.example.raildistance.data.remote.StationDto
import com.example.raildistance.ui.theme.KoTheme

@Composable
fun StationItem(station: StationDto, onClick: (StationDto) -> Unit = {}) {
    Row(
        modifier = Modifier
            .clickable { onClick.invoke(station) }
            .padding(
                start = KoTheme.kODimensions.paddingS,
                end = KoTheme.kODimensions.paddingS,
                top = KoTheme.kODimensions.paddingL,
                bottom = KoTheme.kODimensions.paddingL
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = stringResource(id = R.string.station),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(KoTheme.kODimensions.padding))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = station.name ?: "",
                style = KoTheme.kOTypography.stationName
            )
            Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingXs))
            Text(
                text = "${station.city}, ${station.region}, ${station.country}",
                style = KoTheme.kOTypography.stationDescription
            )
        }
        Spacer(modifier = Modifier.height(KoTheme.kODimensions.paddingS))
        Icon(
            modifier = Modifier
                .wrapContentSize(),
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = station.name,
            tint = Color.Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StationItemPreview() {
    StationItem(
        station = StationDto(
            name = "Warszawa Centralna",
            city = "Warszawa",
            region = "mazowieckie",
            country = "Poland"
        )
    )
}