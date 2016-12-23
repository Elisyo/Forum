import java.sql.SQLException;
import java.util.ArrayList;

import forum.action.GroupAction;
import forum.action.LogAction;
import forum.action.UserAction;
import forum.bean.data.MessageGroup;
import forum.bean.data.MessageNotification;
import forum.bean.data.RequestNotification;
import forum.presentation.ConnectionFrame;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new ConnectionFrame();
		
		LogAction.getInstance().connexion("dylan", "user");

		ArrayList<MessageNotification> list = UserAction.getInstance().getMessageNotification();
		for (int i =0;i<list.size();i++){
			System.out.println(list.get(i).getEnvoyeur() + " a écrit un message au groupe "+list.get(i).getGroupe().getNomGroupe()+" !");
		}
	}

}
