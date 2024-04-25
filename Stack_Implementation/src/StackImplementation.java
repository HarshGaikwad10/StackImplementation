
import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StackImplementation extends Application{
    
    private ListView<String> listView;
    private Button push_btn, pop_btn, peek_btn, search_btn;
    private TextField textField = new TextField();
    private Stack<String> stack = new Stack<>();
    private Label output_label, stack_label;
    private ImageView view = new ImageView("./");
    private static int count = 0;
    
    
    public static void main(String[] stack) {

        launch(stack);
    }

    private Stage window;
    @Override
    public void start(Stage stage) {
        window = stage;
        push_btn = new Button("Push");
        push_btn.setPrefSize(100, 25);
        push_btn.setOnAction(this::methodCall);
        push_btn.setStyle("-fx-background-color: #0096FF;-fx-font-weight:bold");

        pop_btn = new Button("Pop");
        pop_btn.setPrefSize(100, 25);
        pop_btn.setOnAction(this::methodCall);
        pop_btn.setStyle("-fx-background-color: #0096FF;-fx-font-weight:bold");

        peek_btn = new Button("Peek");
        peek_btn.setPrefSize(100, 25);
        peek_btn.setOnAction(this::methodCall);
        peek_btn.setStyle("-fx-background-color: #0096FF;-fx-font-weight:bold");

        search_btn = new Button("Search");
        search_btn.setPrefSize(100, 25);
        search_btn.setOnAction(this::methodCall);
        search_btn.setStyle("-fx-background-color: #0096FF;-fx-font-weight:bold");

        textField = new TextField();
        textField.setPrefSize(100, 25);
        textField.setOnAction(this::methodCall);
        textField.setStyle("-fx-font-weight: bold");

        output_label = new Label();
        output_label.setStyle("-fx-font-weight: bold");
        
        stack_label = new Label();
        stack_label.setStyle("-fx-border-width: 2; -fx-border-color: black");
        stack_label.setPrefWidth(360);

        HBox hb_head = new HBox(view);
        hb_head.setStyle("-fx-padding: 20;");
        hb_head.setPrefHeight(180);

        HBox hb_btn_textField = new HBox(50, pop_btn, textField);
        HBox hb_output_label = new HBox(50,output_label);
        hb_output_label.setAlignment(Pos.CENTER);

        VBox vb_methods = new VBox(30,push_btn,hb_btn_textField,peek_btn,search_btn,output_label);
        vb_methods.setPrefWidth(600);
        vb_methods.setAlignment(Pos.CENTER_LEFT);
        vb_methods.setPadding(new Insets(30));
        listView = new ListView<>();

        listView.setCellFactory(param -> new ListCell<String>(){
            // @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item,empty);
                if(empty || item==null){
                    setText(null);
                    setStyle("");
                }else{
                    setText(item);
                    setStyle("-fx-background-color: #A9C7C5; -fx-border-wodth:2; -fx-border-color: #0d394f");
                }
                setAlignment(Pos.CENTER);
            }
        });

        listView.setPrefSize(50,700);

        VBox vb_stack = new VBox(10, listView);
        vb_stack.setPadding(new Insets(0,30,30,0));
        vb_stack.setPrefWidth(400);
        vb_stack.setAlignment(Pos.CENTER);
        
        HBox hb_impl = new HBox(10,vb_methods,vb_stack);
        hb_impl.setPrefHeight(600);

        VBox vb_screen = new VBox(10, hb_head, hb_impl);

        BorderPane root = new BorderPane(vb_screen);

        Scene scene = new Scene(root, 1000, 700);
        window.setScene(scene);
        window.setTitle("Stack Implementation");
        window.show();
    }

    public void methodCall(ActionEvent event){
        Object source = event.getSource();
        if(source == push_btn){
            String item = textField.getText();
            if(!item.isEmpty()){
                stack.push(item);
                count++;
                listView.getItems().add(0,item);
                textField.clear();
            } else{
                output_label.setText("Please Enter Element:");
            }
        }else if(source == pop_btn){
            if(!stack.isEmpty()){
                stack.pop();
                count--;
                output_label.setText(listView.getItems().get(0));
                listView.getItems().remove(0);
            }else{
                output_label.setText("Stack is Empty");
            }
        }else if(source == peek_btn){
            if(!stack.isEmpty()){
                output_label.setText(listView.getItems().get(0));
            }else{
                output_label.setText("Stack is Empty");
            }
        }else if(source == search_btn){
            int search = -1;
            if(!textField.getText().isEmpty()){
                if(!stack.isEmpty()){
                    int i = count;
                    String arr[] = new String[count];
                    listView.getItems().toArray(arr);
                    while(--i >= 0){
                        if(arr[i].equals(textField.getText()))
                            search = i;
                    }
                    if(search != -1)
                        output_label.setText("Element found at: "+(++search));
                    else
                        output_label.setText("Element not found");
                }else{
                    output_label.setText("Stack is empty");
                }
                textField.clear();
            }else{
                output_label.setText("Please Enter Element");
            }
        }
    }
}
