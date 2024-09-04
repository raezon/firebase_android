package in.macrocodes.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // User is signed in
            Toast.makeText(this, "User is signed in", Toast.LENGTH_SHORT).show();
        } else {
            // No user is signed in, so sign in anonymously
            signInAnonymously();
        }
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign-in success
                Log.d("FirebaseAuth", "Anonymous sign-in successful");
                Toast.makeText(MainActivity.this, "Signed in anonymously", Toast.LENGTH_SHORT).show();
            } else {
                // Sign-in failed
                Log.w("FirebaseAuth", "Anonymous sign-in failed", task.getException());
                Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
