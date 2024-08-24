package com.oza.cipraaiassigment;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.oza.cipraaiassigment.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.progressBar.setVisibility(View.GONE);

        binding.signInBtn.setOnClickListener(v -> {
            if (binding.emailEditText.getText().toString().isEmpty()) {
                binding.emailTextInputLayout.setError("Please enter your email");
            }
            if (binding.passwordEditText.getText().toString().isEmpty()) {
                binding.passwordTextInputLayout.setError("Please enter your password");
            }
            if (!binding.emailEditText.getText().toString().isEmpty() || !binding.passwordEditText.getText().toString().isEmpty()) {
                userCredentialCheck(binding.emailEditText.getText().toString(), binding.passwordEditText.getText().toString());
            }
        });
    }

    private void userCredentialCheck(String email, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<String> call = apiInterface.signIn(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    binding.emailEditText.setText("");
                    binding.passwordEditText.setText("");
                    binding.passwordTextInputLayout.setError("Invalid username/password");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MyCall", "Error");
                binding.progressBar.setVisibility(View.GONE);
                binding.emailEditText.setText("");
                binding.passwordEditText.setText("");
                binding.passwordTextInputLayout.setError("Something went wrong");
            }
        });
    }
}