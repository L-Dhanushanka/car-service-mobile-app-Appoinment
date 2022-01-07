package com.example.car_service_wecarcare;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainApAdapter extends FirebaseRecyclerAdapter<MainApModel,MainApAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainApAdapter(@NonNull FirebaseRecyclerOptions<MainApModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainApModel model) {

        holder.vehicleNo.setText(model.getVehicleNo());
        holder.vehicleName.setText(model.getVehicleName());
        holder.appointmentDate.setText(model.getAppointmentDate());
        holder.appointmentTime.setText(model.getAppointmentTime());
        holder.anySpecialInstruction.setText(model.getAnySpecialInstruction());


       //update popup
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.ap_update_popup))
                        .setExpanded(true,1300)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();

                final EditText vehicleNo =view.findViewById(R.id.txtVno);
                final EditText vehicleName =view.findViewById(R.id.txtVname);
                final EditText appointmentDate =view.findViewById(R.id.txtApdate);
                final EditText appointmentTime =view.findViewById(R.id.txtAptime);
                final EditText anySpecialInstruction =view.findViewById(R.id.txtAsi);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                vehicleNo.setText(model.getVehicleNo());
                vehicleName.setText(model.getVehicleName());
                appointmentDate.setText(model.getAppointmentDate());
                appointmentTime.setText(model.getAppointmentTime());
                anySpecialInstruction.setText(model.getAnySpecialInstruction());

                dialogPlus.show();


                //update the details
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map= new HashMap<>();
                        map.put("vehicleNo",vehicleNo.getText().toString());
                        map.put("vehicleName",vehicleName.getText().toString());
                        map.put("appointmentDate",appointmentDate.getText().toString());
                        map.put("appointmentTime",appointmentTime.getText().toString());
                        map.put("anySpecialInstruction",anySpecialInstruction.getText().toString());
                        //database connection
                        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().
                                child("appoinment")
                                .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {@Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.vehicleNo.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.vehicleNo.getContext(), "Error while Updating ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                         }
                });


            }
        });

        //Delete
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.vehicleNo.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted Appointment  can't br undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/")
                                .getReference().child("appoinment")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

                     }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(holder.vehicleNo.getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_ap,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView vehicleNo,vehicleName,appointmentDate,appointmentTime,anySpecialInstruction;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.apimg);
            vehicleNo = (TextView)itemView.findViewById(R.id.vnotext);
            vehicleName = (TextView)itemView.findViewById(R.id.vnametext);
            appointmentDate = (TextView)itemView.findViewById(R.id.apdatetext);
            appointmentTime = (TextView)itemView.findViewById(R.id.aptimetext);
            anySpecialInstruction = (TextView)itemView.findViewById(R.id.asitext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }

}
