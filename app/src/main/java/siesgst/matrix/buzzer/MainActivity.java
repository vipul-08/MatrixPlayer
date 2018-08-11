package siesgst.matrix.buzzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner team;
    EditText ip;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        team = findViewById(R.id.playerNum);
        ip = findViewById(R.id.ipAddress);
        submit = findViewById(R.id.nextBtn);

        ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>( this , android.R.layout.simple_spinner_item , android.R.id.text1);
        playerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        playerAdapter.add("Select your Team:");
        playerAdapter.add("Team A");
        playerAdapter.add("Team B");
        playerAdapter.add("Team C");
        playerAdapter.add("Team D");

        team.setAdapter(playerAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(team.getSelectedItemPosition() == 0 || ip.getText().toString().equals("") ) {
                    Toast.makeText(MainActivity.this,"Please select a team and enter an IP properly..",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,BuzzerActivity.class);
                    intent.putExtra("ip_address",ip.getText().toString());
                    intent.putExtra("teamId",team.getSelectedItemPosition());
                    startActivity(intent);
                }
            }
        });
    }
}