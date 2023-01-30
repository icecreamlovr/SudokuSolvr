import React from "react";
import ReactDOM from 'react-dom';

class Tile extends React.Component {
  constructor(props) {
    super(props);
  }

  handleInput = (event) => {
    event.preventDefault();
    const filteredValue = event.target.value.replace(/[^1-9]/g, '');
    if (filteredValue != "") {
      // So that invalid inputs (e.g. alphabeticals) are not populated to
      // parent which would clear the tile.
      this.props.propagateValue(filteredValue);
    } else if (event.target.value == "") {
      // So that delete (backspace) can be populated and clear the tile.
      // Note this checks the original input value, not the filtered one.
      this.props.propagateValue(filteredValue);
    }
  }

  render() {
    return (
      <input
        className="tile"
        type="text"
        maxLength="1"
        onInput={this.handleInput}
        value={this.props.value} />
    );
  }
}

class Board extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      value0: ""
    };
  }

  handleTilePropagatesValue = (value) => {
    this.setState({ value0: value });
  }

  render() {
    return (
      <div>
        <div className="board-row">
          <Tile value={this.state.value0} propagateValue={this.handleTilePropagatesValue} />
        </div>
      </div>
    );
  }
}

const root = ReactDOM.createRoot(document.getElementById("react-mountpoint"));
root.render(<Board />);
