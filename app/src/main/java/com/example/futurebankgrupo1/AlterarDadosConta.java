package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityAlterarDadosContaBinding;
import com.example.futurebankgrupo1.databinding.ActivityDadosContaBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlterarDadosConta extends AppCompatActivity {
    ActivityAlterarDadosContaBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlterarDadosContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();




//        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                .setDisplayName("Jane Q. User")
//                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
//                .build();
//
//        user.updateProfile(profileUpdates);
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User profile updated.");
//                        }
//                    }
//                });



    }
}