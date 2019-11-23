import React, {Component} from 'react';

 class Movies extends Component {

    constructor(props) {
       super(props);
       this.state  = {
        movies: [],
           isLoaded:false
       }
    }

    componentDidMount() {
        fetch('https://facebook.github.io/react-native/movies.json')
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            movies: json.movies,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }

    handlerClickItem(id) {
       this.props.history.push('/movie/' + id)
    }

  render() {

    var  {isLoaded, movies} =this.state;

    if(!isLoaded) {
        return <div>Loading...</div>
    }
   else
   {
    return (
      <div>
      <ul className="listMovies">
               {
                  movies.map(item => (
                     <li key={item.id} onClick={this.handlerClickItem.bind(this,item.title)}> {item.title}</li>
                  ))
               }
            </ul>
      </div>
     );
   }
  }
}
export default Movies;