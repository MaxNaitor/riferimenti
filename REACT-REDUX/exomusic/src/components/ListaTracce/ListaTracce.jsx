import Popover from 'react-bootstrap/Popover';
const ListaTracce = (tracce) => {
    return (
        <Popover id="popover-basic">
            <Popover.Title as="h3">Tracklist</Popover.Title>
            <Popover.Content>
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Titolo</th>
                            <th>Durata</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tracce.map(traccia =>
                            <tr key={traccia.id}>
                                <td>{traccia.titolo}</td>
                                <td>{traccia.durata}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </Popover.Content>
        </Popover>
    )
}

export default ListaTracce;