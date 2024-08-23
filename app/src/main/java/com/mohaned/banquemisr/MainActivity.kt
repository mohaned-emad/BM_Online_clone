package com.mohaned.banquemisr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaned.banquemisr.ui.theme.BanqueMisrTheme
import com.mohaned.banquemisr.ui.theme.DarkRed
import com.mohaned.banquemisr.ui.theme.LightRed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrTheme {
                MainAppDesign()
            }
        }
    }
}

@Composable
fun MainAppDesign(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = modifier.padding(30.dp))
        HeaderDesign()
        Spacer(modifier = modifier.padding(30.dp))
        LoginForm()
        Spacer(modifier = modifier.padding(30.dp))
        FooterDesign()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MainAppPreview() {
    MainAppDesign()
}

@Composable
fun HeaderDesign(modifier: Modifier = Modifier) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bm_icon),
            contentDescription = stringResource(R.string.banque_misr_icon)
        )

        Text(
            text = stringResource(R.string.language),
            color = DarkRed,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun FooterDesign(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = modifier.padding(bottom = 20.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            IconWithLabel(
                icon = R.drawable.our_products,
                label = stringResource(R.string.our_products)
            )
            IconWithLabel(
                icon = R.drawable.exchange_rate,
                label = stringResource(R.string.exchange_rate)
            )
            IconWithLabel(
                icon = R.drawable.security_tips,
                label = stringResource(R.string.security_tips)
            )
            IconWithLabel(
                icon = R.drawable.nearest_branch_or_atm,
                label = stringResource(R.string.nearest_branch_or_atm)
            )
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var visiblePassword by remember {
        mutableStateOf(false)
    }

    val validForm = username != "" && password != ""

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it},
            label = {Text(
                text = stringResource(R.string.username),
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
            ),
            modifier = modifier
                .fillMaxWidth(),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            label = {Text(
                text = stringResource(R.string.password),
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
            )},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            visualTransformation = if (visiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(
                    painter = if (visiblePassword) {
                        painterResource(id = R.drawable.baseline_visibility_24)
                    } else {
                        painterResource(id = R.drawable.baseline_visibility_off_24)
                    },
                    contentDescription = "Hide password icon",
                )
                IconButton(onClick = { visiblePassword = !visiblePassword}){}
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Text(
            text = stringResource(R.string.forgot_username_password),
            textDecoration = TextDecoration.Underline,
            color = Color.DarkGray,
            modifier = modifier
                .align(Alignment.Start)
                .padding(top = 20.dp),
        )

        Button(
            onClick = {},
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkRed,
                disabledContainerColor = LightRed,
                disabledContentColor = Color.White,
                contentColor = Color.White,
            ),
            enabled = validForm,
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = stringResource(R.string.login), fontWeight = FontWeight.Bold)
        }

        Row(
            modifier = modifier
                .align(Alignment.Start)
                .padding(top = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.need_help),
                color = Color.DarkGray
            )
            Text(
                text = stringResource(R.string.contact_us),
                modifier = modifier
                    .padding(start = 2.dp),
                textDecoration = TextDecoration.Underline,
                color = DarkRed,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun IconWithLabel(icon: Int, label : String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(72.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(60.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
    }
}