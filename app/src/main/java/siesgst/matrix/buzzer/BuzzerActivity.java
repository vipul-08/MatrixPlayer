package siesgst.matrix.buzzer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class BuzzerActivity extends AppCompatActivity {

    TextView teamNameText;
    Button buzzButton;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzzer);

        mediaPlayer = MediaPlayer.create(BuzzerActivity.this,R.raw.buzzer);
        teamNameText = findViewById(R.id.teamName);
        buzzButton = findViewById(R.id.buzzButton);

        final String ip = getIntent().getStringExtra("ip_address");
        final int teamId = getIntent().getIntExtra("teamId",0);
        String teamName = "";
        switch (teamId) {
            case 1  :   teamName = "Team A";
                        break;
            case 2  :   teamName = "Team B";
                break;
            case 3  :   teamName = "Team C";
                break;
            case 4  :   teamName = "Team D";
                break;
        }

        teamNameText.setText(teamName);

        buzzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new UdpSender(teamId,ip)).start();
                mediaPlayer.start();
            }
        });
    }
    class UdpSender implements Runnable {

        int message;
        String ipAdd;

        UdpSender(int message , String ipAdd) {
            this.message = message;
            this.ipAdd = ipAdd;
        }

        @Override
        public void run() {
            try {
                DatagramSocket udpSocket = new DatagramSocket(1111);
                udpSocket.setReuseAddress(true);
                InetAddress serverAddress = InetAddress.getByName(ipAdd);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream pout = new PrintStream(baos);
                pout.print(message);
                byte[] buffer = baos.toByteArray();
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length,serverAddress,1111);
                udpSocket.send(packet);
                udpSocket.close();
            } catch (IOException e) {
                Log.d("ExceptionHua",e+"");
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}
