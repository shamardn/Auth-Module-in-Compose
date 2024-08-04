package com.shamardn.authmodulecompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shamardn.authmodulecompose.presentation.components.AuthButton
import com.shamardn.authmodulecompose.presentation.components.BubbleAnimation
import com.shamardn.authmodulecompose.presentation.components.HeaderBackground
import com.shamardn.authmodulecompose.presentation.components.NavDestinationHelper
import com.shamardn.authmodulecompose.presentation.components.TextEntryModule
import com.shamardn.authmodulecompose.presentation.viewmodel.LoginViewModel
import com.shamardn.authmodulecompose.presentation.viewmodel.RegisterViewModel
import com.shamardn.authmodulecompose.ui.theme.gray
import com.shamardn.authmodulecompose.ui.theme.orange
import com.shamardn.authmodulecompose.ui.theme.white
import com.shamardn.authmodulecompose.ui.theme.whiteGray
import com.shamardn.authmodulecompose.ui.theme.whiteGrayOrange
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {

    NavDestinationHelper(
        shouldNavigate = { registerViewModel.registerState.isSuccessfullyRegistered },
        destination = onRegisterSuccessNavigation
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderBackground(
                leftColor = white,
                rightColor = whiteGrayOrange,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
            Text(
                text = "Register",
                style = MaterialTheme.typography.headlineLarge,
                color = white,
                fontWeight = FontWeight.SemiBold,
            )
        }
        RegisterContainer(
            emailValue = { registerViewModel.registerState.emailInput },
            passwordValue = { registerViewModel.registerState.passwordInput },
            passwordRepeatedValue = { registerViewModel.registerState.passwordRepeatedInput },
            buttonEnabled = { registerViewModel.registerState.isInputVaIid },
            onEmailValueChange = registerViewModel::onEmailInputChange,
            onPasswordValueChange = registerViewModel::onPasswordInputChange,
            onPasswordRepeatedValueChange = registerViewModel::onPasswordRepeatedInputChange,
            onRegisterButtonClicked = registerViewModel::onRegisterClick,
            isPasswordVisible = { registerViewModel.registerState.isPasswordShown },
            isPasswordRepeatedVisible = { registerViewModel.registerState.isPasswordRepeatedShown },
            onTrailingPasswordIconClicked = registerViewModel::onTogglePasswordVisibility,
            onTrailingPasswordRepeatedIconClicked = registerViewModel::onTogglePasswordRepeatedVisibility,
            errorHint = { registerViewModel.registerState.errorMessageInput },
            isLoading = { registerViewModel.registerState.isLoading },
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxWidth(0.9f)
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(whiteGray, RoundedCornerShape(16.dp))
                .padding(10.dp, 15.dp, 10.dp, 5.dp)
                .align(Alignment.TopCenter)
        )
        BubbleAnimation(
            bubbleColor1 = white,
            bubbleColor2 = orange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyLarge,
                color = orange,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        onNavigateToLoginScreen()
                    }
            )

        }
    }

}


@Composable
fun RegisterContainer(
    emailValue: () -> String,
    passwordValue: () -> String,
    passwordRepeatedValue: () -> String,
    buttonEnabled: () -> Boolean,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onPasswordRepeatedValueChange: (String) -> Unit,
    onRegisterButtonClicked: () -> Unit,
    isPasswordVisible: () -> Boolean,
    isPasswordRepeatedVisible: () -> Boolean,
    onTrailingPasswordIconClicked: () -> Unit,
    onTrailingPasswordRepeatedIconClicked: () -> Unit,
    errorHint: () -> String?,
    isLoading: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            textValue = emailValue(),
            description = "Email Address",
            hint = "email@example.com",
            leadingIcon = Icons.Default.Email,
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onEmailValueChange,
            trailingIcon = null,
            onTrailingIconClick = null
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            textValue = passwordValue(),
            description = "Enter Password",
            hint = "password",
            leadingIcon = Icons.Default.VpnKey,
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordValueChange,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = { onTrailingPasswordIconClicked() },
            visualTransformation = if (isPasswordVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyBoardType = KeyboardType.Password,
        )
        TextEntryModule(
            modifier = Modifier
                .fillMaxWidth(),
            textValue = passwordRepeatedValue(),
            description = "Re-enter Password",
            hint = "re-enter password",
            leadingIcon = Icons.Default.VpnKey,
            textColor = gray,
            cursorColor = orange,
            onValueChanged = onPasswordRepeatedValueChange,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = { onTrailingPasswordRepeatedIconClicked() },
            visualTransformation = if (isPasswordRepeatedVisible()) VisualTransformation.None else PasswordVisualTransformation(),
            keyBoardType = KeyboardType.Password,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AuthButton(
                text = "Register",
                backgroundColor = orange,
                contentColor = white,
                enabled = buttonEnabled(),
                onButtonClick = onRegisterButtonClicked,
                isLoading = isLoading(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
            Text(
                errorHint() ?: "",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}
