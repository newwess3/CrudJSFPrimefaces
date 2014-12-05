$(function(){
    
    $(document).ready(function() {
        $(function() {
            
            /*$( ".datepicker" ).datepicker({
                numberOfMonths: 3,
                showButtonPanel: true,
                regional: "pt-BR"
            });*/
            $.datepicker.setDefaults( $.datepicker.regional[ "" ] );
            $( ".datepicker" ).datepicker( $.datepicker.regional[ "pt-BR" ] );
            $( "#locale" ).change(function() {
                $( ".datepicker" ).datepicker( "option",
                    $.datepicker.regional[ $( this ).val() ]);                                  
            });
        });
        
    });

    /*$(document).ready(function(){
        $('#submit').click(function(){
            var nome = $('#in-nome').val();
            var turma = $('#in-turma').val();
            var email = $('#in-email').val();
            var dataCadastro = $('#dataCadastro').val();
            var bool = true;

            if(nome == "" || nome == null) {
                $('#span-nome').show();
                bool = false;
            }else{
                $('#span-nome').hide();
            }

            if(turma == "" || turma == null) {
                $('#span-turma').show();
                bool = false;
            }else{
                $('#span-turma').hide();
            }

            if(email == "" || email == null) {
                $('#span-email').show();
                bool = false;
            }else{
                $('#span-email').hide();
            }

            if(dataCadastro == "" || dataCadastro == null) {
                $('#span-dataCadastro').show();
                bool = false;
            }else{
                $('#span-dataCadastro').hide();
            }

            return bool;
        });
    });*/

});