import React, { Component } from 'react';
import Popover from 'react-bootstrap/esm/Popover';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

class Traccia extends Component {
    constructor(props) {
        super(props);
        this.state = {
            titolo: '',
            durata: ''
        }
    }

    aggiungiTraccia = () => {
        if (this.validaInput()) {
            this.props.aggiungi(this.state);
            this.setState({
                titolo: '',
                durata: ''
            })
        }
    }

    handleChange = (event) => {
        this.setState ({
            [event.target.id]: event.target.value
        })
    }

    validaInput = () => {
        if (this.state.titolo === '' || this.state.durata === '') {
            withReactContent(Swal).fire({
                title: <p>Tutti i campi sono obbligatori!</p>
            })
            return false;
        }
        return true;
    }

    render() {
        return (
            <Popover id="popover-basic"{...this.props}>
                <Popover.Title as="h3">Traccia</Popover.Title>
                <Popover.Content>
                    <label>Titolo</label> <br />
                    <input type="text" id="titolo" value={this.state.titolo} onChange={this.handleChange} /><br /><br />
                    <label>Durata</label> <br />
                    <input type="text" id="durata" value={this.state.durata} onChange={this.handleChange} /><br /><br />
                    <button onClick={this.aggiungiTraccia} className="btn btn-primary">Aggiungi</button>
                </Popover.Content>
            </Popover>
        );
    }
}
export default Traccia;
