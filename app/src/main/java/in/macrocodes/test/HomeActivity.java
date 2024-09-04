package in.macrocodes.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        Button signOutButton = findViewById(R.id.signOutButton);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            welcomeTextView.setText("Welcome, " + currentUser.getEmail());
        }

        signOutButton.setOnClickListener(v -> signOut());
    }

    private void signOut() {
        mAuth.signOut();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
