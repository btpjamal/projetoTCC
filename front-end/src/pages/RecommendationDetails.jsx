import { useNavigate, useParams } from "react-router";
import "./css/Recommendations.css";

export default function RecommendationDetails() {
    const { hobbyId } = useParams();
    const navigate = useNavigate();

    return (
      <main className="details-container">
          <section className="details-card">
              <h1>Detalhes da recomendação</h1>

              <p>Hobby Selecionado: {hobbyId}</p>

              <button onClick={() => navigate("/recommendations")}>
                  Voltar
              </button>
          </section>
      </main>
    );
}