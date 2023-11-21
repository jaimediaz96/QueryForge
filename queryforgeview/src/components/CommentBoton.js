import React from "react";
import commentService from "../services/commentService";

const CommentComponent = () => {
  const commentData = {
    userId: 1,
    queryId: 1,
    commentText: "Este es un comentario de ejemplo",
  };

  const handleGetAll = () => {
    commentService
      .getAll(0, 10, "userId", "ASC")
      .then((data) => console.log("Get All Comments:", data))
      .catch((error) => console.error(error));
  };

  const handleGetById = () => {
    const commentId = 1;
    commentService
      .getById(commentId)
      .then((data) => console.log(`Get Comment By ID ${commentId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleGetAllByUserId = () => {
    const userId = 1;
    commentService
      .getAllByUserId(userId, 0, 10, "userId", "ASC")
      .then((data) => console.log(`Get Comments By User ID ${userId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleGetAllByQueryId = () => {
    const queryId = 1;
    commentService
      .getAllByUserId(queryId, 0, 10, "userId", "ASC")
      .then((data) => console.log(`Get Comments By User ID ${queryId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleAddComment = () => {
    commentService
      .add(commentData)
      .then((data) => console.log("Comment Added:", data))
      .catch((error) => console.error(error));
  };

  const handleDeleteComment = () => {
    const commentId = 1;
    commentService
      .delete(commentId)
      .then((data) => console.log(`Comment Deleted ID ${commentId}:`, data))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <button onClick={handleGetAll}>Get All Comments</button>
      <button onClick={handleGetById}>Get Comment By ID</button>
      <button onClick={handleGetAllByUserId}>Get Comments By User ID</button>
      <button onClick={handleGetAllByQueryId}>Get Comments By User ID</button>
      <button onClick={handleAddComment}>Add Comment</button>
      <button onClick={handleDeleteComment}>Delete Comment</button>
    </div>
  );
};

export default CommentComponent;
