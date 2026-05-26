import { useEffect, useState } from "react";
import { api } from "../api/api";

export default function Recommendations() {
  const [recommendations, setRecommendations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState(null);

  useEffect(() => {
    async function carregarRecomendacoes() {
      try {
        setLoading(true);

        const token = localStorage.getItem("token");

        const response = await api.get(
          "http://localhost:8080/api/v1/recommendations/3",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          },
        );

        setRecommendations(response.data);
      } catch (error) {
        console.error(error);
        console.error("status:", error.response?.status);
        console.error("Resposta:", error.response?.data);

        setErro("Não foi possível carregar as recomendações");
      } finally {
        setLoading(false);
      }
    }

    carregarRecomendacoes();
  }, []);

  if (loading) return <p>Carregando recomendações...</p>;
  if (erro) return <p>{erro}</p>;

  return (
    <div>
      <h1>Recomendações</h1>

      {recommendations.length === 0 ? (
        <p>Nenhuma recomendação encontrada.</p>
      ) : (
        recommendations.map((item) => (
          <div
            key={item.hobbyId}
            style={{
              border: "1px solid #ccc",
              padding: 15,
              marginBottom: 10,
              borderRadius: 10,
            }}
          >
            <h2>{item.nome}</h2>
            <p>Categoria: {item.categoria}</p>
            <p>Score: {item.score}</p>
            <p>{item.motivo}</p>
          </div>
        ))
      )}
    </div>
  );
}
