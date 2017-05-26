
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project.database_1.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.List;

import com.rapleaf.jack.test_project.database_1.IDatabase1;
import com.rapleaf.jack.LazyLoadPersistence;
import com.rapleaf.jack.queries.GenericQuery;
import com.rapleaf.jack.BaseDatabaseConnection;
import com.rapleaf.jack.queries.Records;
import com.rapleaf.jack.queries.Column;
import com.rapleaf.jack.queries.QueryFetcher;
import com.rapleaf.jack.test_project.database_1.iface.ICommentPersistence;
import com.rapleaf.jack.test_project.database_1.iface.IImagePersistence;
import com.rapleaf.jack.test_project.database_1.iface.ILockableModelPersistence;
import com.rapleaf.jack.test_project.database_1.iface.IPostPersistence;
import com.rapleaf.jack.test_project.database_1.iface.IUserPersistence;

import com.rapleaf.jack.test_project.IDatabases;
import com.rapleaf.jack.tracking.PostQueryAction;

public class Database1Impl implements IDatabase1 {
  
  private final BaseDatabaseConnection conn;
  private final IDatabases databases;
  private final PostQueryAction postQueryAction;
  private final LazyLoadPersistence<ICommentPersistence, IDatabases> comments;
  private final LazyLoadPersistence<IImagePersistence, IDatabases> images;
  private final LazyLoadPersistence<ILockableModelPersistence, IDatabases> lockable_models;
  private final LazyLoadPersistence<IPostPersistence, IDatabases> posts;
  private final LazyLoadPersistence<IUserPersistence, IDatabases> users;

  public Database1Impl(BaseDatabaseConnection conn, IDatabases databases, PostQueryAction postQueryAction) {
    this.conn = conn;
    this.databases = databases;
    this.postQueryAction = postQueryAction;
    this.comments = new LazyLoadPersistence<>(conn, databases, BaseCommentPersistenceImpl::new);
    this.images = new LazyLoadPersistence<>(conn, databases, BaseImagePersistenceImpl::new);
    this.lockable_models = new LazyLoadPersistence<>(conn, databases, BaseLockableModelPersistenceImpl::new);
    this.posts = new LazyLoadPersistence<>(conn, databases, BasePostPersistenceImpl::new);
    this.users = new LazyLoadPersistence<>(conn, databases, BaseUserPersistenceImpl::new);
  }

  public GenericQuery.Builder createQuery() {
    final GenericQuery.Builder builder = GenericQuery.create(conn);
    builder.setPostQueryAction(postQueryAction);
    return builder;
  }

  @Override
  public Records findBySql(String statement, List<?> params, Set<Column> columns) throws IOException {
    final PreparedStatement preparedStatement = conn.getPreparedStatement(statement);
    try {
      for (int i = 0; i < params.size(); i++) {
        final Object param = params.get(i);
        final int paramIdx = i+1;
        preparedStatement.setObject(paramIdx, param);
      }
      return QueryFetcher.getQueryResults(preparedStatement, columns, conn);
    } catch (SQLException e) {
      throw new IOException(e);
    }
  }

  public ICommentPersistence comments(){
    return comments.get();
  }

  public IImagePersistence images(){
    return images.get();
  }

  public ILockableModelPersistence lockableModels(){
    return lockable_models.get();
  }

  public IPostPersistence posts(){
    return posts.get();
  }

  public IUserPersistence users(){
    return users.get();
  }

  public boolean deleteAll() throws IOException {
    boolean success = true;
    success &= comments().isEmpty() || comments().deleteAll();
    success &= images().isEmpty() || images().deleteAll();
    success &= lockableModels().isEmpty() || lockableModels().deleteAll();
    success &= posts().isEmpty() || posts().deleteAll();
    success &= users().isEmpty() || users().deleteAll();
    return success;
  }

  public void disableCaching() {
    comments.disableCaching();
    images.disableCaching();
    lockable_models.disableCaching();
    posts.disableCaching();
    users.disableCaching();
  }

  public void setAutoCommit(boolean autoCommit) {
    conn.setAutoCommit(autoCommit);
  }

  public boolean getAutoCommit() {
    return conn.getAutoCommit();
  }

  public void commit() {
    conn.commit();
  }

  public void rollback() {
    conn.rollback();
  }

  public void resetConnection() {
    conn.resetConnection();
  }

  @Override
  public void close() throws IOException {
    conn.close();
  }

  public IDatabases getDatabases() {
    return databases;
  }

}
