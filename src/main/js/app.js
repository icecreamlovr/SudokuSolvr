import React from "react";
import ReactDOM from 'react-dom';

class Tile extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: ""
    };
  }

  handleInput = (event) => {
    event.preventDefault();
    const filteredValue = event.target.value.replace(/[^1-9]/g, '');
    if (filteredValue != "") {
      // So that invalid inputs (e.g. alphabeticals) doesn't clear the tile
      this.setState({ value: filteredValue });
    } else if (event.target.value == "") {
      // So that delete (backspace) can clear the tile.
      // Note this checks the original input value, not the filtered one.
      this.setState({ value: filteredValue });
    }
  }

  render() {
    return (
      <input
        className="tile"
        type="text"
        maxLength="1"
        onInput={this.handleInput}
        value={this.state.value} />
    );
  }
}

class Board extends React.Component {
  render() {
    return (
      <div>
        <div className="board-row">
          <Tile value="7" />
        </div>
      </div>
    );
  }
}

const root = ReactDOM.createRoot(document.getElementById("react-mountpoint"));
root.render(<Board />);
