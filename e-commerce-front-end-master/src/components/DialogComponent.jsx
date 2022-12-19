import React from 'react';
import {
    Button ,
    Dialog ,
    DialogActions ,
    DialogTitle ,
    Slide
} from "@mui/material";
import { useNavigate } from 'react-router-dom';


const Transition = React.forwardRef ( function Transition(props , ref) {
    return <Slide direction="up" ref={ ref } { ...props } />;
} );

// COMPONENTE UTILIZZATO NEL CASO DI CLICK SU AGGIUNTA AI PREFERITI O AL CARRELLO QUANDO L'UTENTE NON è LOGGATO

const DialogComponent = (props) => {
    const navigate = useNavigate();

    return (
        <Dialog
            open={ props.dialogEliminazioneFlag }
            onClose={props.handleClose}
            maxWidth={ 'xs' }
            TransitionComponent={ Transition }
            keepMounted
            aria-describedby="alert-dialog-slide-description"
        >
            <DialogTitle>{ "Per continuare questa azione devi essere loggato." }</DialogTitle>
            <DialogActions>
                <Button
                    variant={ "outlined" }
                    color={ "error" }
                    onClick={props.handleClose}
                >Anulla</Button>
                <Button
                    variant={ "outlined" }
                    color={ "primary" }
                    onClick={ () => {
                        navigate("/login")
                    } }
                >Vai al Login</Button>
            </DialogActions>
        </Dialog>
    );
};

export default DialogComponent;