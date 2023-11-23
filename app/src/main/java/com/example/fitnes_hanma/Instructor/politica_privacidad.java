package com.example.fitnes_hanma.Instructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;
import com.example.fitnes_hanma.R;

public class politica_privacidad extends AppCompatActivity {
    TextView n1,n2,n3,n4,n5,n6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidad);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(politica_privacidad.this, ConfiguracionIns.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Politicas de privacidad");

        n1 = (TextView) findViewById(R.id.n1);
        n2 = (TextView) findViewById(R.id.n2);
        n3 = (TextView) findViewById(R.id.n3);
        n4 = (TextView) findViewById(R.id.n4);
        n5 = (TextView) findViewById(R.id.n5);
        n6 = (TextView) findViewById(R.id.n6);

        n1.setText("1.1 Esta Política de Privacidad rige la recopilación, el uso y el almacenamiento de la información obtenida de los Usuarios a través del uso de la aplicación FitnessHanma y los Servicios de la Aplicación." +
                " No vendemos ni ponemos a disposición información personal a terceros. Utilizamos la información exclusivamente para nuestros fines internos." +
                " En algunos casos, estamos obligados a proporcionar información o partes de ella a organismos autorizados. La información sobre los usuarios se divide en información identificable y no identificable " +
                "dependiendo de si la información puede identificar al usuario como una persona específica. La Política de Privacidad se aplica a todos los Usuarios," +
                " independientemente de la versión de la Aplicación que estén utilizando.");
        n2.setText("2.1 Nos reservamos el derecho de cambiar nuestra Política de Privacidad en cualquier momento sin previo aviso. La versión actual de la Política de Privacidad está disponible en la Aplicación indicando la fecha de entrada en vigor." +
                " Si los cambios en la Política de privacidad son de naturaleza significativa, se lo notificaremos por correo electrónico, si dicha información está disponible, antes de que los cambios entren en vigencia. " +
                "Le recomendamos que consulte periódicamente nuestra Política de privacidad.");
        n3.setText("3.1 Al descargar y utilizar la Aplicación, usted acepta estar sujeto a los términos de nuestra Política de privacidad, las leyes aplicables y las políticas de Internet. Los servicios de la aplicación están destinados a un público general y no están dirigidos a niños. " +
                "Nos tomamos muy en serio la seguridad en línea de los niños, y si alguna vez nos enteramos de que la información recopilada pertenece a un niño, eliminaremos inmediatamente dicha información a menos que dicha información se " +
                "proporcione con el consentimiento verificable de los padres y cumpla con las leyes pertinentes.");
        n4.setText("4.1 Podemos mantener registros de cualquier pregunta, queja o cumplido realizado por usted y la respuesta, si la hubiera. Siempre que se ponga en contacto con nosotros, recopilaremos su nombre, dirección de correo electrónico y cualquier información adicional que nos proporcione y la almacenaremos en servidores seguros para brindarle el mejor servicio posible y mejorar nuestros Servicios.\n" +
                "\n" +
                "4.2 Si nos proporciona su dirección de correo electrónico, acepta recibir correos electrónicos periódicos de nuestra parte. Es posible que nos pongamos en contacto con usted utilizando la información de contacto disponible que nos haya proporcionado, para cualquier asunto relacionado con el Servicio. También podemos informarle por correo electrónico sobre noticias, promociones, ofertas especiales, anuncios u otros temas de interés relacionados con FitnesHanma. Puede optar por dejar de recibir estos correos electrónicos promocionales en cualquier momento.\n" +
                "\n" +
                "4.3 Nos esforzamos por mantener la seguridad física, técnica y de procedimiento adecuada con respecto a instalaciones de almacenamiento de información para evitar cualquier pérdida, uso indebido, acceso no autorizado, divulgación o modificación de la información personal, lo que también se aplica a nuestra eliminación o destrucción de información personal. Mantenemos la información personal que recopilamos sobre usted estrictamente confidencial. Solo el personal autorizado tiene acceso a esta información personal.\n" +
                "\n" +
                "4.4 Si inicia sesión en la aplicación y proporciona su correo electrónico, se almacena en Firebase. Además, su nombre completo se recopilará a medida que se guarde. Tiene la opción de eliminar su cuenta usando la aplicación, tras lo cual se borrarán todos sus datos.\n" +
                "4.5 Utilizamos su información para proporcionar y mejorar nuestros servicios, personalizar los servicios para usted, comprender mejor a nuestros Usuarios, diagnosticar y solucionar problemas, etc.\n" +
                "\n" +
                "4.6 No vendemos ninguna información recopilada a terceros.");
        n5.setText("5.1 se realizaran recopilaciones de bloqueos dentro de la Aplicación. Esta información no es personalmente identificable");
        n6.setText("6.1 Si tiene alguna pregunta sobre nuestra Política de privacidad y cómo se maneja la información, o si desea acceder, modificar o actualizar su información, no dude en ponerse en contacto con nosotros en el apartado de Contactanos");

    }
}