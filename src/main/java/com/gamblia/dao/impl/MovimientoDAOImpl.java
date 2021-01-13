package com.gamblia.dao.impl;

import com.gamblia.dao.spi.MovimientoDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Movimiento;
import com.gamblia.model.criteria.MovimientoCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAOImpl implements MovimientoDAO {

    private final Logger logger = LogManager.getLogger(JuegoDAOImpl.class.getName());
    private final String select = " SELECT ID, ID_CARTERA_OR, ID_CARTERA_DEST, ASUNTO, CANTIDAD, ID_OPERACION, FECHA ";

    public MovimientoDAOImpl() {
    }

    @Override
    public Movimiento findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM MOVIMIENTOS WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Movimiento movimiento = null;
                if (resultSet.next()) {
                    movimiento = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Movimiento {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return movimiento;
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return null;
    }

    @Override
    public List<Movimiento> findBy(Connection connection, MovimientoCriteria movimientoCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", movimientoCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Movimiento> movimientos = new ArrayList<>();
        if (connection != null && movimientoCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM MOVIMIENTOS ");

                boolean first = true;

                if (movimientoCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (movimientoCriteria.getIdCarteraOrigen() != null) {
                    DAOUtils.addClause(query, first, " ID_CARTERA_OR = ? ");
                    first = false;
                }
                if (movimientoCriteria.getIdCarteraDestino() != null) {
                    DAOUtils.addClause(query, first, " ID_CARTERA_DEST = ? ");
                    first = false;
                }
                if (movimientoCriteria.getAsunto() != null) {
                    DAOUtils.addClause(query, first, " ASUNTO = ? ");
                    first = false;
                }
                if (movimientoCriteria.getCantidad() != null) {
                    DAOUtils.addClause(query, first, " CANTIDAD = ? ");
                    first = false;
                }
                if (movimientoCriteria.getIdOperacion() != null) {
                    DAOUtils.addClause(query, first, " ID_OPERACION = ? ");
                    first = false;
                }
                if (movimientoCriteria.getFecha() != null) {
                    DAOUtils.addClause(query, first, " FECHA = ? ");
                    first = false;
                }
                if (movimientoCriteria.getFechaDesde() != null) {
                    DAOUtils.addClause(query, first, " FECHA > ? ");
                    first = false;
                }
                if (movimientoCriteria.getFechaHasta() != null) {
                    DAOUtils.addClause(query, first, " FECHA < ? ");
                }


                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (movimientoCriteria.getId() != null) preparedStatement.setInt(i++, movimientoCriteria.getId());
                if (movimientoCriteria.getIdCarteraOrigen() != null)
                    preparedStatement.setInt(i++, movimientoCriteria.getIdCarteraOrigen());
                if (movimientoCriteria.getIdCarteraDestino() != null)
                    preparedStatement.setInt(i++, movimientoCriteria.getIdCarteraDestino());
                if (movimientoCriteria.getAsunto() != null)
                    preparedStatement.setString(i++, movimientoCriteria.getAsunto());
                if (movimientoCriteria.getCantidad() != null)
                    preparedStatement.setDouble(i++, movimientoCriteria.getCantidad());
                if (movimientoCriteria.getIdOperacion() != null)
                    preparedStatement.setInt(i++, movimientoCriteria.getIdOperacion());
                if (movimientoCriteria.getFecha() != null)
                    preparedStatement.setDate(i++, (java.sql.Date) movimientoCriteria.getFecha());
                if (movimientoCriteria.getFechaDesde() != null)
                    preparedStatement.setDate(i++, (java.sql.Date) movimientoCriteria.getFechaDesde());
                if (movimientoCriteria.getFechaHasta() != null)
                    preparedStatement.setDate(i, (java.sql.Date) movimientoCriteria.getFechaHasta());

                resultSet = preparedStatement.executeQuery();

                Movimiento movimiento;

                while (resultSet.next()) {
                    movimiento = loadNext(resultSet);
                    movimientos.add(movimiento);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return movimientos;
    }

    @Override
    public List<Movimiento> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Movimiento> movimientos = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM MOVIMIENTOS ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Movimiento movimiento;

                while (resultSet.next()) {
                    movimiento = loadNext(resultSet);
                    movimientos.add(movimiento);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return movimientos;
    }

    @Override
    public Movimiento update(Connection connection, Movimiento movimiento) {
        if (logger.isDebugEnabled()) logger.debug("update movimiento: {}", movimiento);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && movimiento != null) {
            try {
                query = new StringBuilder(select).append(" FROM MOVIMIENTOS ");

                boolean first = true;

                if (movimiento.getId() != null) {
                    DAOUtils.addUpdate(query, true, " ID = ? ");
                    first = false;
                }
                if (movimiento.getIdCarteraOrigen() != null) {
                    DAOUtils.addUpdate(query, first, " ID_CARTERA_OR = ? ");
                    first = false;
                }
                if (movimiento.getIdCarteraDestino() != null) {
                    DAOUtils.addUpdate(query, first, " ID_CARTERA_DEST = ? ");
                    first = false;
                }
                if (movimiento.getAsunto() != null) {
                    DAOUtils.addUpdate(query, first, " ASUNTO = ? ");
                    first = false;
                }
                if (movimiento.getCantidad() != null) {
                    DAOUtils.addUpdate(query, first, " CANTIDAD = ? ");
                    first = false;
                }
                if (movimiento.getIdOperacion() != null) {
                    DAOUtils.addUpdate(query, first, " ID_OPERACION = ? ");
                    first = false;
                }
                if (movimiento.getFecha() != null) {
                    DAOUtils.addUpdate(query, first, " FECHA = ? ");
                }


                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (movimiento.getId() != null) preparedStatement.setInt(i++, movimiento.getId());
                if (movimiento.getIdCarteraOrigen() != null)
                    preparedStatement.setInt(i++, movimiento.getIdCarteraOrigen());
                if (movimiento.getIdCarteraDestino() != null)
                    preparedStatement.setInt(i++, movimiento.getIdCarteraDestino());
                if (movimiento.getAsunto() != null)
                    preparedStatement.setString(i++, movimiento.getAsunto());
                if (movimiento.getCantidad() != null)
                    preparedStatement.setDouble(i++, movimiento.getCantidad());
                if (movimiento.getIdOperacion() != null)
                    preparedStatement.setInt(i++, movimiento.getIdOperacion());
                if (movimiento.getFecha() != null)
                    preparedStatement.setDate(i, (java.sql.Date) movimiento.getFecha());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Mesa no actualizada");
                    return movimiento;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de mesa duplicado");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return null;
    }

    @Override
    public Movimiento create(Connection connection, Movimiento movimiento) {
        if (logger.isDebugEnabled()) logger.debug("Create mesa: {}", movimiento);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (movimiento != null && movimiento.getIdCarteraOrigen() != null && movimiento.getAsunto() != null
                && movimiento.getCantidad() != null && movimiento.getIdOperacion() != null) {
            try {
                String query = " INSERT INTO MOVIMIENTOS (ID_CARTERA_OR, ID_CARTERA_DEST, ASUNTO, CANTIDAD, ID_OPERACION)" +
                        " values (?,?,?,?,?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setInt(i++, movimiento.getIdCarteraOrigen());
                preparedStatement.setInt(i++, movimiento.getIdCarteraDestino());
                preparedStatement.setString(i++, movimiento.getAsunto());
                preparedStatement.setDouble(i++, movimiento.getCantidad());
                preparedStatement.setInt(i++, movimiento.getIdOperacion());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Mesa'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    movimiento.setId(pk);
                    return movimiento;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Movimiento loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Movimiento movimiento = new Movimiento();
        movimiento.setId(resultSet.getInt(i++));
        movimiento.setIdCarteraOrigen(resultSet.getInt(i++));
        movimiento.setIdCarteraDestino(resultSet.getInt(i++));
        movimiento.setAsunto(resultSet.getString(i++));
        movimiento.setCantidad(resultSet.getDouble(i++));
        movimiento.setIdOperacion(resultSet.getInt(i++));
        movimiento.setFecha(resultSet.getDate(i));
        return movimiento;
    }

}
